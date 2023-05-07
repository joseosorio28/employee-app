package com.amaris.employeeapp.dao.entities;

import com.amaris.employeeapp.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Response {

    private String status;
    private Employee employee;
    private String message;

}
