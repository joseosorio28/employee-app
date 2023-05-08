package com.amaris.employeeapp.model;

import com.amaris.employeeapp.utils.CustomStringDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServerResponse {

    private String status;
    @JsonDeserialize(using = CustomStringDeserializer.class)
    private List<Employee> data;
    private String message;

}
