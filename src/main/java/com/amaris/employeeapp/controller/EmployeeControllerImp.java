package com.amaris.employeeapp.controller;

import com.amaris.employeeapp.model.EmployeeDto;
import com.amaris.employeeapp.business.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1.0/")
public class EmployeeControllerImp implements EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeControllerImp(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public ResponseEntity<EmployeeDto> getEmployee(String employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    public ResponseEntity<List<EmployeeDto>>  getEmployees() {
        return employeeService.getEmployees();
    }

}
