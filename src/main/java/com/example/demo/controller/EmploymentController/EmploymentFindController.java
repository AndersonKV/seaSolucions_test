package com.example.demo.controller.EmploymentController;

import com.example.demo.service.EmploymentService.EmploymentFindService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/employment")
@CrossOrigin("*")
@Api(value="API REST FIND EMPLOYMENT")
public class EmploymentFindController {
    @Autowired
    private EmploymentFindService employmentFindService;

    @ApiOperation(value="should find all employments")
    @GetMapping(path = "find_all")
    public ResponseEntity ResponseEntityFindAll() {
        return this.employmentFindService.findAll();
    }

    @ApiOperation(value="should find employment by name")
    @GetMapping(path = "find_by_employment")
    public ResponseEntity findByEmploymentName(@RequestParam("name") String name) {
        return this.employmentFindService.findEmploymentByName(name);
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
