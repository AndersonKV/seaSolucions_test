package com.example.demo.controllers.SectorController;

import com.example.demo.controllers.DTO.SectorDTO;
import com.example.demo.entities.Sector;
import com.example.demo.service.SectorService.SectorCreateService;
import com.example.demo.service.SectorService.SectorFindService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
