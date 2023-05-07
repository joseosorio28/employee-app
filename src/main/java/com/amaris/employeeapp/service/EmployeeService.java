package com.amaris.employeeapp.service;

import com.amaris.employeeapp.dao.EmployeeRepository;
import com.amaris.employeeapp.dao.entities.Response;
import com.amaris.employeeapp.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<Employee> getEmployee(String employeeId){

        String response = employeeRepository.getEmployee(employeeId);

        Employee employee = Employee
                .builder()
                .name(response)
                .build();

        System.out.println(response);

        ResponseEntity<Employee> responseEntity = ResponseEntity
                .ok()
                .body(employee);

        return responseEntity;

    }


    public ResponseEntity<Employee> getEmployees() {

        String response = employeeRepository.getEmployees();

        Employee employee = Employee
                .builder()
                .name(response)
                .build();

        System.out.println(response);

        ResponseEntity<Employee> responseEntity = ResponseEntity
                .ok()
                .body(employee);

        return responseEntity;


    }
}
