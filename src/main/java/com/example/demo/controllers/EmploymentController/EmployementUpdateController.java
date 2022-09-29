package com.example.demo.controllers.EmploymentController;

import com.example.demo.DTO.EmploymentDTO.EmploymentUpdateDTO;
import com.example.demo.entities.Employment;
import com.example.demo.service.EmploymentService.EmploymentCreateService;
import com.example.demo.service.EmploymentService.EmploymentUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/employment")
@CrossOrigin("*")
public class EmployementUpdateController {
    @Autowired
    private EmploymentUpdateService employmentUpdateService;

    @PutMapping(path = "id/{id}")
    public ResponseEntity Update(@PathVariable Long id, @Valid @RequestBody EmploymentUpdateDTO request) {
        return this.employmentUpdateService.update(id, request);
    }

}
