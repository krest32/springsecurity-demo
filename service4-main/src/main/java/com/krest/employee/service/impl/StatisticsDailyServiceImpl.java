package com.krest.employee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.krest.employee.entity.StatisticsDaily;
import com.krest.employee.mapper.StatisticsDailyMapper;
import com.krest.employee.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.krest.employee.service.TblService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author krest
 * @since 2021-05-23
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    StatisticsDailyService statisticsDailyService;

    @Override
    public Integer countRegisterByDay(String day) {
        Integer count = baseMapper.selectRegisterCount(day);
        return count;
    }

    /**
     * 定时任务，每天向数据库中添加统计的数据
     */
    @Override
    public void createStatisticsByDay(String day) {
        //删除已存在的统计对象
        QueryWrapper<StatisticsDaily> dayQueryWrapper = new QueryWrapper<>();
        dayQueryWrapper.eq("date_calculated", day);
        baseMapper.delete(dayQueryWrapper);

        //获取统计信息
        Integer registerNum = (Integer) statisticsDailyService.countRegisterByDay(day);

        //创建统计对象
        StatisticsDaily daily = new StatisticsDaily();
        daily.setRegisterNum(registerNum);
        daily.setDateCalculated(day);

        baseMapper.insert(daily);
    }

    @Override
    public Map<String, Object> getChartData(String begin, String end, String type) {
        // 建立搜索条件，获取指定日期内的所有数据
        QueryWrapper<StatisticsDaily> dayQueryWrapper = new QueryWrapper<>();
        dayQueryWrapper.select(type, "date_calculated");
        dayQueryWrapper.between("date_calculated", begin, end);

        List<StatisticsDaily> dayList = baseMapper.selectList(dayQueryWrapper);

        // 针对数据生成指定的集合
        Map<String, Object> map = new HashMap<>();
        List<Integer> dataList = new ArrayList<Integer>();
        List<String> dateList = new ArrayList<String>();

        map.put("dataList", dataList);
        map.put("dateList", dateList);


        for (int i = 0; i < dayList.size(); i++) {
            StatisticsDaily daily = dayList.get(i);
            dateList.add(daily.getDateCalculated());
            switch (type) {
                case "register_num":
                    dataList.add(daily.getRegisterNum());
                    break;
                default:
                    break;
            }
        }
        return map;
    }
}
