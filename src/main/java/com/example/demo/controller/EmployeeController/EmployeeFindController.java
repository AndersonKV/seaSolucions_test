package com.example.demo.controller.EmployeeController;

import com.example.demo.entities.Employee;
import com.example.demo.service.EmployeeService.EmployeeFindService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/employee")
@CrossOrigin("*")
@Api(value="API REST FIND EMPLOYEE")
public class EmployeeFindController {

    @Autowired
    private EmployeeFindService employeeFindService;

    @ApiOperation(value="should find all employees")
    @GetMapping
    public ResponseEntity findAll() {
        return this.employeeFindService.findAll();
    }

    @ApiOperation(value="should find employee by id")
    @GetMapping(path = "id/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return this.employeeFindService.findById(id);
    }

    @ApiOperation(value="should employees by name")
    @GetMapping(path = "find_by_name")
    public ResponseEntity<List<Employee>> FindByName(@RequestParam("name") String name) {
        return this.employeeFindService.findByName(name);
    }
    @ApiOperation(value="should find by cpf")
    @GetMapping(path = "find_by_cpf")
    public ResponseEntity FindByCpf(@RequestParam("cpf") String cpf) {
        return this.employeeFindService.findByCpf(cpf);
    }


}
