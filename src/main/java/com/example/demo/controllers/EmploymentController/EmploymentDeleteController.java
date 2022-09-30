package com.example.demo.controllers.EmploymentController;

import com.example.demo.repository.EmploymentRepository;
import com.example.demo.service.EmploymentService.EmploymentDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/employment")
@CrossOrigin("*")
public class EmploymentDeleteController {
    @Autowired
    private EmploymentDeleteService employmentDeleteService;

    @DeleteMapping(path = "id/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        return this.employmentDeleteService.deleteById(id);
    }


    @DeleteMapping
    public ResponseEntity deleteById() {
        return this.employmentDeleteService.deleteAll();
    }
}
