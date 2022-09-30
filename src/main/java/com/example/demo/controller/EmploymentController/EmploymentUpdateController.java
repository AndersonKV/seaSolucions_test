package com.example.demo.controller.EmploymentController;

import com.example.demo.DTO.EmploymentDTO.EmploymentUpdateDTO;
import com.example.demo.service.EmploymentService.EmploymentUpdateService;
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
@RequestMapping(path = "api/v1/employment")
@CrossOrigin("*")
@Api(value="API REST UPDATE EMPLOYMENT")
public class EmploymentUpdateController {
    @Autowired
    private EmploymentUpdateService employmentUpdateService;

    @ApiOperation(value="should update employment")
    @PutMapping(path = "update/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody EmploymentUpdateDTO request) {
         return this.employmentUpdateService.update(id, request);
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
