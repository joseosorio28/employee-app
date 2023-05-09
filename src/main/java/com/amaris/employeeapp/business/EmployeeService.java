package com.amaris.employeeapp.business;

import com.amaris.employeeapp.dao.EmployeeRepository;
import com.amaris.employeeapp.model.Employee;
import com.amaris.employeeapp.model.EmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<EmployeeDto> getEmployee(String employeeId) {

        Employee employee = employeeRepository.getEmployee(employeeId);
        EmployeeDto employeeDto = mapToDto(employee);
        return ResponseEntity
                .ok()
                .body(employeeDto);
    }

    public ResponseEntity<List<EmployeeDto>> getEmployees() {

        List<Employee> employees = employeeRepository.getEmployees();
        List<EmployeeDto> employeeDtos = mapToDtos(employees);
        return ResponseEntity
                .ok()
                .body(employeeDtos);
    }

    private EmployeeDto mapToDto(Employee employee) {

        return EmployeeDto
                .builder()
                .id(employee.getId())
                .name(employee.getName())
                .salary(employee.getSalary())
                .annualSalary(calculateAnnualSalary(employee.getSalary()))
                .age(employee.getAge())
                .profileImage(employee.getProfileImage())
                .build();

    }

    private List<EmployeeDto> mapToDtos(List<Employee> employees) {

        return employees.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private long calculateAnnualSalary(long salary) {
        return salary * 12;
    }
}
