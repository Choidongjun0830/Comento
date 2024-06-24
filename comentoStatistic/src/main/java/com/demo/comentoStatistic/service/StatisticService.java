package com.demo.comentoStatistic.service;

import com.demo.comentoStatistic.dao.StatisticMapper;
import com.demo.comentoStatistic.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {

    @Autowired //매퍼 DI
    StatisticMapper statisticMapper;

    public YearCountDto getYearLogins(String year) {
        return statisticMapper.selectYearLogin(year);
    }

    public YearMonthCountDto getYearMonthLogins(String year, String month) {
        return statisticMapper.selectYearMonthLogin(year + month);
    }

    public YearMonthDayCountDto getYearMonthDayLogins(String year, String month, String day) {
        return statisticMapper.selectYearMonthDayLogin(year + month + day);
    }

    public AvgDayCountDto getAvgDayLoginCount() {
        return statisticMapper.selectAvgDayLoginCount();
    }

    public List<DepartmentMonthCountDto> getDepartmentMonthLoginCount() {
        return statisticMapper.selectDepartmentMonthLoginCount();
    }

    public NonHolidayDto getNonHolidayLoginCount() {
        return statisticMapper.selectNonHolidayLoginCount();
    }
}
