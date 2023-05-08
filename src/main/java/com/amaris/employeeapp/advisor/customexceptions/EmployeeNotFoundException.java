package com.amaris.employeeapp.advisor.customexceptions;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String id) {
        super("Could not find employee with id: " + id);
    }
}

