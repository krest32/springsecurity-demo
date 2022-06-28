package com.krest.employee.controller;


import com.krest.common.Response.R;
import com.krest.employee.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author krest
 * @since 2021-05-23
 */

@Api(tags = "统计数据功能")
@RestController
@RequestMapping("/employee/statistics-daily")
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService statisticsDailyService;


    @ApiOperation("得到某天的统计人数")
    @GetMapping(value = "countregister/{day}")
    public R registerCount(@PathVariable String day){
        Integer count = statisticsDailyService.countRegisterByDay(day);
        return R.ok().data("countRegister", count);
    }



    @ApiOperation("在数据库中生成指定日期的统计数据")
    @GetMapping(value = "createDailyCount/{day}")
    public R createDailyCount(@PathVariable String day){
        statisticsDailyService.createStatisticsByDay(day);
        return R.ok();
    }

    @ApiOperation("获取指定日期的数据信息")
    @GetMapping("show-chart/{begin}/{end}/{type}")
    public R showChart(@PathVariable String begin,@PathVariable String end,@PathVariable String type){
        Map<String, Object> map = statisticsDailyService.getChartData(begin, end, type);
        return R.ok().data(map);
    }

}

