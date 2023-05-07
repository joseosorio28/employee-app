package com.amaris.employeeapp.dao;

import com.amaris.employeeapp.dao.entities.Response;
import org.springframework.http.ResponseEntity;

public interface EmployeeRepository {

    String getEmployee(String id);
    String getEmployees();

}
