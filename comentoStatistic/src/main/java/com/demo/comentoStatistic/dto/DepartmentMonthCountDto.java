package com.demo.comentoStatistic.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DepartmentMonthCountDto {
    String departmentName;
    String yearMonth;
    Integer totCnt;
}
