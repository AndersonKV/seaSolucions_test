package com.example.demo.controllers.EmploymentController;

import com.example.demo.repository.EmploymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/employment")
@CrossOrigin("*")
public class EmploymentDeleteController {
    @Autowired
    private EmploymentRepository employmentRepository;

    public ResponseEntity deleteById(Long id) {
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
