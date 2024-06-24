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

import java.util.List;

@Controller
public class StatisticController {

    @Autowired
    StatisticService statisticService;
    //해당 년도의 로그인 수
    @RequestMapping(value = "/api/v1/logins/{year}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<YearCountDto> getYearLoginCount(@PathVariable("year") String year) {
        return ResponseEntity.ok(statisticService.getYearLogins(year));
    }
    //해당 년월의 로그인 수 -> 월별 접속자 수
    @RequestMapping(value = "/api/v1/logins/{year}/{month}", produces = "application/json")
    @ResponseBody
    public Object getYearMonthLoginCount(@PathVariable("year") String year, @PathVariable("month") String month) {
        return ResponseEntity.ok(statisticService.getYearMonthLogins(year,month));
    }

    //일자별 접속자 수
    @RequestMapping(value = "api/v1/logins/{year}/{month}/{day}", produces = "application/json")
    @ResponseBody
    public Object getYearMonthDayLoginCount(@PathVariable("year") String year, @PathVariable("month") String month, @PathVariable("day") String day) {
        return ResponseEntity.ok(statisticService.getYearMonthDayLogins(year,month, day));
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
}
