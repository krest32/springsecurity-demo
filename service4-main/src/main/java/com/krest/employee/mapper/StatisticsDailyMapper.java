package com.krest.employee.mapper;

import com.krest.employee.entity.StatisticsDaily;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 网站统计日数据 Mapper 接口
 * </p>
 *
 * @author krest
 * @since 2021-05-23
 */
public interface StatisticsDailyMapper extends BaseMapper<StatisticsDaily> {

    Integer selectRegisterCount(String day);
}
