package com.amaris.employeeapp.dao;

import com.amaris.employeeapp.advisor.customexceptions.EmployeeNotFoundException;
import com.amaris.employeeapp.advisor.customexceptions.RequestException;
import com.amaris.employeeapp.advisor.customexceptions.ServerException;
import com.amaris.employeeapp.model.Employee;
import com.amaris.employeeapp.model.ServerResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class EmployeeRepositoryImp implements EmployeeRepository {

    private final RestTemplate restTemplate;

    @Value("${repository.endpoint}")
    private String repositoryEndpoint;
    @Value("${repository.resource}")
    private String resource;
    @Value("${repository.resources}")
    private String resources;

    public EmployeeRepositoryImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Employee getEmployee(String id) {

        String urlEmployee = repositoryEndpoint + resource + id;
        ResponseEntity<ServerResponse> responseEntity;
        HttpStatus status;

        try {
            responseEntity = restTemplate.getForEntity(urlEmployee, ServerResponse.class);
        } catch (Exception e) {
            if (e.getMessage().contains("429")) {
                throw new RequestException();
            } else {
                throw new ServerException(e.getMessage());
            }
        }

        status = responseEntity.getStatusCode();
        ServerResponse serverResponse = responseEntity.getBody();

        if (status == HttpStatus.NOT_FOUND) {
            throw new EmployeeNotFoundException(id);
        } else if (status != HttpStatus.OK || serverResponse == null) {
            throw new ServerException("");
        }

        return responseEntity.getBody().getData().get(0);

    }

    @Override
    public List<Employee> getEmployees() {

        String urlEmployee = repositoryEndpoint + resources;
        ResponseEntity<ServerResponse> responseEntity;
        HttpStatus status;

        try {
            responseEntity = restTemplate.getForEntity(urlEmployee, ServerResponse.class);
        } catch (Exception e) {
            if (e.getMessage().contains("429")) {
                throw new RequestException();
            } else {
                throw new ServerException(e.getMessage());
            }
        }

        status = responseEntity.getStatusCode();
        ServerResponse serverResponse = responseEntity.getBody();

        if (status != HttpStatus.OK || serverResponse == null) {
            throw new ServerException("");
        }

        return responseEntity.getBody().getData();

    }

}
