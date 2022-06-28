package com.krest.employee.service;

import com.krest.employee.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author krest
 * @since 2021-05-23
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    Integer countRegisterByDay(String day);

    void createStatisticsByDay(String day);

    Map<String, Object> getChartData(String begin, String end, String type);
}
