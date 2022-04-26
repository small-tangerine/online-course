package com.course.component.cache;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.api.entity.Category;
import com.course.api.vo.admin.CategoryVo;
import com.course.commons.utils.Assert;
import com.course.service.service.CategoryService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 权限缓存
 *
 * @since 2022-03-06
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CategoryTreeCache {
    private final CategoryService categoryService;
    private final MapperFacade mapperFacade;

    private static final Integer CATEGORY_KEY = -1110;
    private Cache<Integer, CategoryVo> categoryVoCache;

    @PostConstruct
    public void init() {
        categoryVoCache = CacheBuilder.newBuilder()
                .expireAfterWrite(Duration.ofDays(15))
                .build();
    }

    /**
     * 返回权限包装数据
     *
     * @return 权限
     */
    public List<CategoryVo> getCategoryVoList() {
        loadData();
        CategoryVo parent = categoryVoCache.getIfPresent(CATEGORY_KEY);
        Assert.notNull(parent, "无效的缓存");
        return ObjectUtil.cloneByStream(parent.getChildren());
    }

    /**
     * 缓存过期
     */
    public void expireAll() {
        categoryVoCache.invalidateAll();
    }

    /**
     * 加载数据
     */
    public void loadData() {
        if (Objects.nonNull(categoryVoCache.getIfPresent(CATEGORY_KEY))) {
            return;
        }
        synchronized (CategoryTreeCache.class) {
            if (Objects.nonNull(categoryVoCache.getIfPresent(CATEGORY_KEY))) {
                return;
            }
            LambdaQueryWrapper<Category> query = Wrappers.lambdaQuery();
            query.select(Category::getId, Category::getParentId, Category::getTitle, Category::getDisplayOrder);
            List<Category> list = categoryService.list(query);
            List<CategoryVo> categoryVoList = list.stream().map(item -> mapperFacade.map(item, CategoryVo.class)).collect(Collectors.toList());
            Map<Integer, CategoryVo> categoryMap = categoryVoList.stream().collect(Collectors.toMap(CategoryVo::getId, Function.identity()));

            List<CategoryVo> permissionVoList = categoryMap.values().stream().peek(item -> {
                Integer parentId = item.getParentId();
                if (Objects.isNull(parentId) || Objects.equals(0, parentId)) {
                    return;
                }
                CategoryVo permissionVo = categoryMap.get(parentId);
                // 将子级挂载在父级下
                if (Objects.nonNull(permissionVo)) {
                    List<CategoryVo> children = permissionVo.getChildren();
                    if (CollectionUtils.isNotEmpty(children)) {
                        children.add(item);
                    } else {
                        permissionVo.setChildren(Lists.newArrayList(item));
                    }
                    // 显示排序
                    permissionVo.getChildren().sort(Comparator.comparingInt(CategoryVo::getDisplayOrder));
                }
            }).filter(item -> Objects.equals(0, item.getParentId()))
                    .sorted(Comparator.comparingInt(CategoryVo::getDisplayOrder)).collect(Collectors.toList());
            //权限
            categoryVoCache.put(CATEGORY_KEY, new CategoryVo().setChildren(permissionVoList));

        }
    }

}
