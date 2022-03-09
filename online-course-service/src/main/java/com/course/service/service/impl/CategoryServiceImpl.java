package com.course.service.service.impl;

import com.course.api.entity.Category;
import com.course.service.mapper.CategoryMapper;
import com.course.service.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
}
