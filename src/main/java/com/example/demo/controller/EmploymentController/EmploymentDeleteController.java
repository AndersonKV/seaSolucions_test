package com.example.demo.controller.EmploymentController;

import com.example.demo.service.EmploymentService.EmploymentDeleteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/employment")
@CrossOrigin("*")
@Api(value="API REST DELETE EMPLOYMENT")
public class EmploymentDeleteController {
    @Autowired
    private EmploymentDeleteService employmentDeleteService;

    @ApiOperation(value="should delete employment by id")
    @DeleteMapping(path = "id/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        return this.employmentDeleteService.deleteById(id);
    }

    @ApiOperation(value="should delete all employment ")
    @DeleteMapping(path = "delete_all")
    public ResponseEntity deleteAll() {
        return this.employmentDeleteService.deleteAll();
    }
}
