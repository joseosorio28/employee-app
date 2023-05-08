package com.amaris.employeeapp.controller;

import com.amaris.employeeapp.model.EmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "default")
public interface EmployeeController {

    @GetMapping("employee/{id}")
    ResponseEntity<EmployeeDto> getEmployee(@PathVariable("id") String id);

    @GetMapping("employee")
    ResponseEntity<List<EmployeeDto>> getEmployees();

}
