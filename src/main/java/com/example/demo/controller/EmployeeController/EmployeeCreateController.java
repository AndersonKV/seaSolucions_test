package com.example.demo.controller.EmployeeController;

import com.example.demo.DTO.EmployeeDTO.EmployeeDTO;
import com.example.demo.service.EmployeeService.EmployeeCreateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(path = "api/v1/employee")
@CrossOrigin("*")
@Api(value="API REST CREATE EMPLOYEE")
public class EmployeeCreateController {

    @Autowired
    private EmployeeCreateService employeeCreateService;

    @ApiOperation(value="should create user")
    @PostMapping(path = "create")
    public ResponseEntity Create(@Valid @RequestBody EmployeeDTO request) {
        return this.employeeCreateService.create(request);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
