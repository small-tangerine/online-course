package com.course.service.service.impl;

import com.course.api.entity.Category;
import com.course.service.mapper.CategoryMapper;
import com.course.service.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 分类 服务实现类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public int findNextDisplayOrder(Integer parentId) {
        Integer maxDisplayOrder = baseMapper.findMaxDisplayOrder(parentId);
        if (Objects.isNull(maxDisplayOrder)) {
            return 1;
        }
        int displayOrderMaxLimit = 65535;
        if (Objects.equals(displayOrderMaxLimit, maxDisplayOrder)) {
            return displayOrderMaxLimit;
        }
        return maxDisplayOrder + 1;
    }

    @Override
    public List<Integer> getParentById(Collection<Integer> categoryIds) {
        if (CollectionUtils.isEmpty(categoryIds)) {
            return Collections.emptyList();
        }
        return lambdaQuery().in(Category::getId, categoryIds)
                .select(Category::getParentId)
                .list().stream().map(Category::getParentId).distinct().collect(Collectors.toList());
    }
}
