package com.course.service.service.impl;

import com.course.api.entity.Bills;
import com.course.service.mapper.BillsMapper;
import com.course.service.service.BillsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账单 服务实现类
 * </p>
 *
 * @author panguangming
 * @since 2022-03-04
 */
@Service
public class BillsServiceImpl extends ServiceImpl<BillsMapper, Bills> implements BillsService {

}
