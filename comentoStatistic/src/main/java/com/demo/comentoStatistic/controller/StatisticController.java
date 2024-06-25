package com.demo.comentoStatistic.controller;

import com.demo.comentoStatistic.dto.DepartmentMonthCountDto;
import com.demo.comentoStatistic.dto.YearCountDto;
import com.demo.comentoStatistic.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Controller
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    //해당 년도의 로그인 수
    @RequestMapping(value = "/api/v1/logins/{year}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<YearCountDto> getYearLoginCount(@PathVariable("year") Integer year) {
        validateYear(year);
        String yearStr = String.format("%02d", year);
        return ResponseEntity.ok(statisticService.getYearLogins(yearStr));
    }

    //해당 년월의 로그인 수 -> 월별 접속자 수
    @RequestMapping(value = "/api/v1/logins/{year}/{month}", produces = "application/json")
    @ResponseBody
    public Object getYearMonthLoginCount(@PathVariable("year") Integer year, @PathVariable("month") Integer month) {
        validateYear(year);
        validateMonth(month);

        String yearStr = String.format("%02d", year);
        String monthStr = String.format("%02d", month);
        return ResponseEntity.ok(statisticService.getYearMonthLogins(yearStr, monthStr));
    }

    //일자별 접속자 수
    @RequestMapping(value = "api/v1/logins/{year}/{month}/{day}", produces = "application/json")
    @ResponseBody
    public Object getYearMonthDayLoginCount(@PathVariable("year") Integer year, @PathVariable("month") Integer month, @PathVariable("day") Integer day) {
        validateYear(year);
        validateMonth(month);
        validateDay(year, month, day);

        String yearStr = String.format("%02d", year);
        String monthStr = String.format("%02d", month);
        String dayStr = String.format("%02d", day);
        return ResponseEntity.ok(statisticService.getYearMonthDayLogins(yearStr, monthStr, dayStr));
    }

    //평균 하루 로그인 수
    @RequestMapping(value = "api/v1/logins/avg", produces = "application/json")
    @ResponseBody
    public Object getAvgDayLoginCount() {
        return ResponseEntity.ok(statisticService.getAvgDayLoginCount());
    }

    ///휴일을 제외한 로그인 수
    @RequestMapping(value = "api/v1/logins/non-holiday", produces = "application/json")
    @ResponseBody
    public Object getNonHolidayLoginCount() {
        return ResponseEntity.ok(statisticService.getNonHolidayLoginCount());
    }

    //부서별 월별 로그인 수
    @RequestMapping(value = "api/v1/logins/department", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<DepartmentMonthCountDto>> getDepartmentLoginCount() {
        return ResponseEntity.ok(statisticService.getDepartmentMonthLoginCount());
    }

    private void validateYear(Integer year) {
        String yearString = String.valueOf(LocalDate.now().getYear());
        int yearLimit = Integer.parseInt(yearString.substring(2));

        if (year < 1 || year > yearLimit) {
            throw new IllegalArgumentException("Year must be greater than 0");
        }
    }
    private void validateMonth(Integer month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12");
        }
    }
    private void validateDay(Integer year, Integer month, Integer day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Day must be between 1 and 31");
        }

        // 4월, 6월, 9월, 11월 일수 유효성 검사
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
            throw new IllegalArgumentException("Month " + month + " has only 30 days");
        }

        // 2월 일수 유효성 검사 (윤년 고려)
        if (month == 2) {
            boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
            int maxDaysInFebruary = isLeapYear ? 29 : 28;
            if (day > maxDaysInFebruary) {
                throw new IllegalArgumentException("February has only " + maxDaysInFebruary + " days in year " + year);
            }
        }

    }
}
