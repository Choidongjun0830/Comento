package com.demo.comentoStatistic.dao;

import com.demo.comentoStatistic.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StatisticMapper {

    YearCountDto selectYearLogin(String year);
    YearMonthCountDto selectYearMonthLogin(String yearMonth);
    YearMonthDayCountDto selectYearMonthDayLogin(String yearMonthDay);
    AvgDayCountDto selectAvgDayLoginCount();
    NonHolidayDto selectNonHolidayLoginCount();
    List<DepartmentMonthCountDto> selectDepartmentMonthLoginCount();
}
