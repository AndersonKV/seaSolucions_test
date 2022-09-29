package com.example.demo.controllers.EmploymentController;

import com.example.demo.DTO.EmploymentDTO;
import com.example.demo.entities.Employment;
import com.example.demo.service.EmploymentService.EmploymentFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/position")
@CrossOrigin("*")
public class EmploymentFindController {

    @Autowired
    private EmploymentFindService employmentFindService;

    @GetMapping(path = "find_all")
    public  List<Employment> FindAll() {
         return  this.employmentFindService.findAll();
    }

    @GetMapping(path = "find_by_position")
    public EmploymentDTO findByName(@RequestParam("name") String name) {
          return this.employmentFindService.findByPositionName(name);
    }
}
