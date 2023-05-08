package com.amaris.employeeapp.utils;

import com.amaris.employeeapp.model.Employee;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class CustomStringDeserializer extends JsonDeserializer<List<Employee>> {

    @Override
    public List<Employee> deserialize(JsonParser jsonParser, DeserializationContext context)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        return mapper.readValue(jsonParser, new TypeReference<List<Employee>>() {
        });
    }
}
