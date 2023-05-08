package com.amaris.employeeapp.dao;

import com.amaris.employeeapp.model.Employee;

import java.util.List;

public interface EmployeeRepository {

    Employee getEmployee(String id);

    List<Employee> getEmployees();

}
