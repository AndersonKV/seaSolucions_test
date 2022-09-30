package com.example.demo.controller.EmployeeController;

import com.example.demo.service.EmployeeService.EmployeeDeleteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/employee")
@CrossOrigin("*")
@Api(value="API REST DELETE EMPLOYEE")
public class EmployeeDeleteController {

    @Autowired
    private EmployeeDeleteService employeeDeleteService;

    @ApiOperation(value="should delete employee by id")
    @DeleteMapping(path = "id/{id}")
    public ResponseEntity findAll(@PathVariable Long id) {
        return this.employeeDeleteService.deleteById(id);
    }

    @ApiOperation(value="should delete all employees")
    @DeleteMapping(path = "delete_all")
    public ResponseEntity deleteAll() {
        return this.employeeDeleteService.deleteAll();
    }


}
