package com.example.demo.controllers.EmployeeController;

import com.example.demo.controllers.DTO.EmployeeDTO;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Position;
import com.example.demo.service.EmployeeService.EmployeeCreateService;
import com.example.demo.service.EmployeeService.EmployeeFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/employee")
@CrossOrigin("*")
public class EmployeeFindController {

    @Autowired
    private EmployeeFindService employeeFindService;

    @GetMapping(path = "id/{id}")
    public EmployeeDTO findById(@PathVariable Long id) {
        return this.employeeFindService.findById(id);
    }

    @GetMapping(path = "find_by_name")
    public List<Employee> FindByName(@RequestParam("name") String name) {
        return this.employeeFindService.findByName(name);
    }

    @GetMapping(path = "find_by_cpf")
    public Employee FindByCpf(@RequestParam("cpf") String cpf) {
        return this.employeeFindService.findByCpf(cpf);
    }


    @GetMapping(path = "info/{id}")
    public EmployeeDTO FindByInfoAboutEmployee(@PathVariable Long id) {
        return this.employeeFindService.findByInfoAboutEmployee(id);
    }

}
