package com.example.demo.controllers.EmployeeController;

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.service.EmployeeService.EmployeeCreateService;

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
public class EmployeeCreateController {

    @Autowired
    private EmployeeCreateService employeeCreateService;

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
