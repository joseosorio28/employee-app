package com.amaris.employeeapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EmployeeDto {

    private int id;
    private String name;
    private int salary;
    private long annualSalary;
    private int age;
    private String profileImage;

}