package com.amaris.employeeapp.dao;

import com.amaris.employeeapp.dao.entities.Response;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class EmployeeRepositoryImp implements EmployeeRepository{

    private final RestTemplate restTemplate;

    @Value("${repository.endpoint}")
    private String repositoryEndpoint;

    public EmployeeRepositoryImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getEmployee(String id) {

        String urlEmployee = repositoryEndpoint + "/employee/" + id;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlEmployee, String.class);
        return responseEntity.getBody();

    }

    @Override
    public String getEmployees() {

        String urlEmployees = repositoryEndpoint + "/employees";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlEmployees, String.class);
        return responseEntity.getBody();

    }

}
