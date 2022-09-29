package com.example.demo.controllers.SectorController;

import com.example.demo.DTO.SectorDTO;
import com.example.demo.service.SectorService.SectorFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/sector")
@CrossOrigin("*")
public class SectorFindController {
    @Autowired
    private SectorFindService sectorFindService;

    @GetMapping
    public List<SectorDTO> FindAll() {
        return this.sectorFindService.findAll();
    }

}
