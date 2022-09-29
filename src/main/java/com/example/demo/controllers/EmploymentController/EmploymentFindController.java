package com.example.demo.controllers.EmploymentController;

import com.example.demo.DTO.EmploymentDTO.EmploymentPopulateDTO;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Employment;
import com.example.demo.service.EmploymentService.EmploymentFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/employment")
@CrossOrigin("*")
public class EmploymentFindController {

    @Autowired
    private EmploymentFindService employmentFindService;

    @GetMapping(path = "find_all")
    public  List<Employment> FindAll() {
         return  this.employmentFindService.findAll();
    }

    @GetMapping(path = "find_by_employment")
    public  EmploymentPopulateDTO findByEmploymentName(@RequestParam("name") String name) {
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
