package com.amaris.employeeapp.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private int id;
    @JsonSetter("employee_name")
    private String name;
    @JsonSetter("employee_salary")
    private int salary;
    @JsonSetter("employee_age")
    private int age;
    @JsonSetter("profile_image")
    private String profileImage;

}
