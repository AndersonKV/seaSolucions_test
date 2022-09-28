package com.example.demo.controllers.PositionController;

import com.example.demo.entities.Employee;
import com.example.demo.entities.Position;
import com.example.demo.service.PositionService.PositionCreateService;
import com.example.demo.service.PositionService.PositionFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/position")
@CrossOrigin("*")
public class PositionFindController {

    @Autowired
    private PositionFindService positionFindService;

    @GetMapping(path = "find_all")
    public  List<Position> FindAll() {
         return  this.positionFindService.findAll();
    }

    @GetMapping(path = "find_by_name")
    public List<Employee> findByName(@RequestParam("name") String name) {
          return this.positionFindService.findByName(name);
    }
}
