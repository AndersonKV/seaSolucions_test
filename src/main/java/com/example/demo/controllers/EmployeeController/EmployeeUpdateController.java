package com.example.demo.controllers.EmployeeController;

import com.example.demo.DTO.EmployeeDTO.EmployeeUpdateDTO;
import com.example.demo.service.EmployeeService.EmployeeUpdateService;
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
public class EmployeeUpdateController {
    @Autowired
    private EmployeeUpdateService employeeUpdateService;

    @PutMapping(path = "update/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody EmployeeUpdateDTO request) {
        return this.employeeUpdateService.update(id, request);
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
