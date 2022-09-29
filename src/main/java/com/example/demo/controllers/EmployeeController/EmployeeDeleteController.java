package com.example.demo.controllers.EmployeeController;

import com.example.demo.service.EmployeeService.EmployeeDeleteService;
import com.example.demo.service.EmployeeService.EmployeeFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/employee")
@CrossOrigin("*")
public class EmployeeDeleteController {

    @Autowired
    private EmployeeDeleteService employeeDeleteService;

    @DeleteMapping(path = "id/{id}")
    public ResponseEntity findAll(@PathVariable Long id) {
        return this.employeeDeleteService.deleteById(id);
    }

    @DeleteMapping(path = "delete_all")
    public ResponseEntity deleteAll() {
        return this.employeeDeleteService.deleteAll();
    }


}
