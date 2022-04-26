package com.course.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.course.admin.config.security.SecurityUtils;
import com.course.api.entity.Category;
import com.course.api.vo.admin.CategoryVo;
import com.course.commons.model.Paging;
import com.course.commons.model.Response;
import com.course.commons.utils.Assert;
import com.course.commons.utils.ResponseHelper;
import com.course.component.cache.CategoryCache;
import com.course.component.cache.CategoryTreeCache;
import com.course.component.component.CategoryComponent;
import com.course.service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

/**
 * 分类
 *
 * @since 2022-03-09
 */
@RequestMapping("/category")
@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;
    private final MapperFacade mapperFacade;
    private final CategoryComponent categoryComponent;
    private final CategoryCache categoryCache;
    private final CategoryTreeCache categoryTreeCache;

    /**
     * 一级分类列表
     *
     * @param page     页码
     * @param pageSize 页大小
     * @param keyword  关键词
     * @return response
     */
    @GetMapping("/list")
    public Response categoryList(Integer page, Integer pageSize, String keyword) {
        LambdaQueryWrapper<Category> query = Wrappers.lambdaQuery();
        // 分页参数
        query.eq(Category::getParentId, 0)
                .like(StringUtils.isNotBlank(keyword), Category::getTitle, StringUtils.trim(keyword))
                .orderByAsc(Category::getDisplayOrder);
        Paging<Category> paging = new Paging<>(page, pageSize);
        categoryService.page(paging, query);
        paging.convert(item -> mapperFacade.map(item, CategoryVo.class));
        return Response.ok(paging);
    }

    /**
     * 二级分类
     *
     * @param parentId 父级ID
     * @param page     页码
     * @param pageSize 页大小
     * @param keyword  关键词
     * @return response
     */
    @GetMapping("/level-list")
    public Response categoryLevelList(@NotNull(message = "父级分类编号不能为空") Integer parentId,
                                      Integer page, Integer pageSize, String keyword) {
        LambdaQueryWrapper<Category> query = Wrappers.lambdaQuery();
        query.eq(Category::getParentId, parentId)
                .like(StringUtils.isNotBlank(keyword), Category::getTitle, StringUtils.trim(keyword))
                .orderByAsc(Category::getDisplayOrder);
        Paging<Category> paging = new Paging<>(page, pageSize);
        categoryService.page(paging, query);
        paging.convert(item -> mapperFacade.map(item, CategoryVo.class));
        return Response.ok(paging);
    }

    /**
     * 更新分类
     *
     * @param categoryVo 分类实体
     * @return response
     */
    @PostMapping("/update")
    public Response categoryUpdate(@RequestBody CategoryVo categoryVo) {
        Integer id = categoryVo.getId();
        Assert.notNull(id, "分类编号不能为空");
        Category byId = categoryService.getById(id);
        Assert.notNull(byId, "分类不存在");

        String title = StringUtils.trim(categoryVo.getTitle());
        Assert.notBlank(title, "分类名称不能为空");
        LambdaQueryWrapper<Category> query = Wrappers.lambdaQuery();
        query.eq(Category::getParentId, byId.getParentId())
                .ne(Category::getId, id)
                .eq(Category::getTitle, title);
        int count = categoryService.count(query);
        Assert.isTrue(Objects.equals(0, count), "分类名称已存在");

        Integer parentId = categoryVo.getParentId();
        Assert.notNull(parentId, "父级分类不能为空");
        if (!Objects.equals(0, parentId)) {
            Category parent = categoryService.getById(parentId);
            Assert.notNull(parent, "父级分类不存在");
        }

        Integer displayOrder = categoryVo.getDisplayOrder();
        if (Objects.isNull(displayOrder)) {
            displayOrder = categoryService.findNextDisplayOrder(parentId);
        }

        Category updateCategory = new Category().setId(id).setTitle(title).setDisplayOrder(displayOrder)
                .setParentId(parentId).setUpdatedAt(LocalDateTime.now()).setUpdatedBy(SecurityUtils.getUserId());
        // 是否修改标题
        boolean isUpdate = Objects.equals(title, byId.getTitle());
        categoryComponent.updateCategory(updateCategory, isUpdate);
        //清除分类缓存
        categoryCache.expireAll();
        categoryTreeCache.expireAll();
        return ResponseHelper.updateSuccess();
    }

    /**
     * 删除分类
     *
     * @param categoryVo 分类实体
     * @return response
     */
    @PostMapping("/delete")
    public Response categoryDelete(@RequestBody CategoryVo categoryVo) {
        Collection<Integer> ids = categoryVo.getIds();
        if (CollectionUtils.isEmpty(ids)) {
            return ResponseHelper.deleteSuccess();
        }
        categoryComponent.deleteCategory(ids);
        // 清除缓存
        categoryCache.expireAll();
        categoryTreeCache.expireAll();
        return ResponseHelper.deleteSuccess();
    }

    /**
     * 创建分类
     *
     * @param categoryVo 分类实体
     * @return response
     */
    @PostMapping("/create")
    public Response categoryCreate(@RequestBody CategoryVo categoryVo) {
        String title = StringUtils.trim(categoryVo.getTitle());
        Integer parentId = categoryVo.getParentId();
        Integer displayOrder = categoryVo.getDisplayOrder();

        Assert.notBlank(title, "分类名称不能为空");
        LambdaQueryWrapper<Category> query = Wrappers.lambdaQuery();
        query.eq(Category::getParentId, parentId)
                .eq(Category::getTitle, title);
        int count = categoryService.count(query);
        Assert.isTrue(Objects.equals(0, count), "分类名称已存在");
        // 是否二级分类
        if (Objects.nonNull(parentId) && !Objects.equals(0, parentId)) {
            Category parent = categoryService.getById(parentId);
            Assert.notNull(parent, "父级分类不存在");
        } else {
            parentId = 0;
        }

        if (Objects.isNull(displayOrder)) {
            displayOrder = categoryService.findNextDisplayOrder(parentId);
        }
        // 新建分类实体
        Category category = new Category().setParentId(parentId).setDisplayOrder(displayOrder)
                .setTitle(title).setCreatedAt(LocalDateTime.now()).setCreatedBy(SecurityUtils.getUserId())
                .setUpdatedAt(LocalDateTime.now()).setUpdatedBy(SecurityUtils.getUserId());
        categoryService.save(category);
        categoryCache.expireAll();
        categoryTreeCache.expireAll();
        return Response.ok("创建分类成功");
    }

    /**
     * 分类选择列表
     *
     * @return response
     */
    @GetMapping("/select")
    public Response categorySelect() {
        return Response.ok(categoryTreeCache.getCategoryVoList());
    }
}
