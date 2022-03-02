package com.course.service.service.impl;

import com.course.api.entity.Carts;
import com.course.service.mapper.CartsMapper;
import com.course.service.service.CartsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车 服务实现类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-02
 */
@Service
public class CartsServiceImpl extends ServiceImpl<CartsMapper, Carts> implements CartsService {

}
