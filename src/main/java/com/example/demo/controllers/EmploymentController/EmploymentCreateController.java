package com.example.demo.controllers.EmploymentController;

import com.example.demo.entities.Employment;
import com.example.demo.service.EmploymentService.EmploymentCreateService;
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
@RequestMapping(path = "api/v1/employment")
@CrossOrigin("*")
public class EmploymentCreateController {
    @Autowired
    private EmploymentCreateService employmentCreateService;

    @PostMapping(path = "create")
    public ResponseEntity Create(@Valid @RequestBody Employment request) {
         return this.employmentCreateService.create(request);
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
