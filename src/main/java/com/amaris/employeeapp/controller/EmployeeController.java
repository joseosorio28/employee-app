package com.amaris.employeeapp.controller;

import com.amaris.employeeapp.model.EmployeeDto;
import com.amaris.employeeapp.model.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "default")
@Tag(name = "Employee", description = "The employee search API")
public interface EmployeeController {

    @GetMapping(value = "employees/{id}", produces = "application/json")
    @Operation(summary = "Get an employee by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = EmployeeDto.class))),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "429", description = "To Many Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<EmployeeDto> getEmployee(
            @Parameter(description = "ID of employee to return", required = true)
            @PathVariable("id") String id);

    @GetMapping(value = "employees", produces = "application/json")
    @Operation(summary = "Get all employees", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = EmployeeDto.class)))),
            @ApiResponse(responseCode = "429", description = "To Many Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<List<EmployeeDto>> getEmployees();

}
