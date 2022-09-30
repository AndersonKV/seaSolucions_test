package com.example.demo.controllers.EmployeeController;

import com.example.demo.entities.Employee;
import com.example.demo.service.EmployeeService.EmployeeFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/employee")
@CrossOrigin("*")
public class EmployeeFindController {

    @Autowired
    private EmployeeFindService employeeFindService;

    @GetMapping
    public ResponseEntity findAll() {
        return this.employeeFindService.findAll();
    }


    @GetMapping(path = "id/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return this.employeeFindService.findById(id);
    }

    @GetMapping(path = "find_by_name")
    public ResponseEntity<List<Employee>> FindByName(@RequestParam("name") String name) {
        return this.employeeFindService.findByName(name);
    }

    @GetMapping(path = "find_by_cpf")
    public ResponseEntity FindByCpf(@RequestParam("cpf") String cpf) {
        return this.employeeFindService.findByCpf(cpf);
    }


}
