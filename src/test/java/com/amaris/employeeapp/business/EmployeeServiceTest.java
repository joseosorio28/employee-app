package com.amaris.employeeapp.business;

import com.amaris.employeeapp.advisor.customexceptions.EmployeeNotFoundException;
import com.amaris.employeeapp.dao.EmployeeRepository;
import com.amaris.employeeapp.model.Employee;
import com.amaris.employeeapp.model.EmployeeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;
    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService(employeeRepository);
        employee1 = Employee.builder()
                .id(1)
                .name("employee1")
                .salary(1000)
                .age(50)
                .profileImage("base64")
                .build();
        employee2 = Employee.builder()
                .id(2)
                .name("employee2")
                .salary(2000)
                .age(60)
                .profileImage("base64")
                .build();
    }

    @Test
    void canGetEmployee() {
        //Given
        //employee1
        String id = "1";

        //When
        when(employeeRepository.getEmployee(id)).thenReturn(employee1);
        ResponseEntity<EmployeeDto> response = employeeService.getEmployee(id);

        //Then
        verify(employeeRepository).getEmployee(id);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee1.getId(), response.getBody().getId());
    }

    @Test
    void wontFindEmployee() {
        //Given
        //employee1
        String id = "1";

        //When
        when(employeeRepository.getEmployee(id)).thenThrow(EmployeeNotFoundException.class);

        //Then
        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.getEmployee(id));
    }

    @Test
    void canGetEmployees() {
        //Given
        List<Employee> employees = new ArrayList<>(Arrays.asList(employee1, employee2));

        //When
        when(employeeRepository.getEmployees()).thenReturn(employees);
        ResponseEntity<List<EmployeeDto>> response = employeeService.getEmployees();

        //Then
        verify(employeeRepository).getEmployees();
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employees.size(), response.getBody().size());
        assertEquals(employee1.getId(), response.getBody().get(0).getId());
        assertEquals(employee2.getId(), response.getBody().get(1).getId());
    }
}