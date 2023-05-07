package com.amaris.employeeapp.controller;

import com.amaris.employeeapp.dao.EmployeeRepository;
import com.amaris.employeeapp.model.Employee;
import com.amaris.employeeapp.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "api/v1.0")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/{id}")
    @ResponseBody
    public ResponseEntity<Employee> getEmployee(
            @PathParam("id") String employeeId){
        return employeeService.getEmployee(employeeId);
    }

    @GetMapping("/employee")
    @ResponseBody
    public ResponseEntity<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

}
