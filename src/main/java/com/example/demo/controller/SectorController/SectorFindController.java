package com.example.demo.controller.SectorController;

import com.example.demo.service.SectorService.SectorFindService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/sector")
@CrossOrigin("*")
@Api(value="API REST FIND SECTOR")
public class SectorFindController {
    @Autowired
    private SectorFindService sectorFindService;

    @ApiOperation(value="should find all sectors")
    @GetMapping
    public ResponseEntity FindAll() {
        return this.sectorFindService.findAll();
    }

    @ApiOperation(value="should find sector by id")
    @GetMapping(path = "id/{id}")
    public ResponseEntity FindById(@PathVariable Long id) {
        return this.sectorFindService.findById(id);
    }

}
