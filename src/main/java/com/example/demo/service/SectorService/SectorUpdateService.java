package com.example.demo.service.SectorService;

import com.example.demo.DTO.SectorDTO;
import com.example.demo.entities.Sector;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.SectorRepository;
import com.example.demo.utils.SectorValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SectorUpdateService {
    @Autowired
    private SectorValidate sectorValidate;
    @Autowired
    private SectorRepository sectorRepository;


    public ResponseEntity update(Long id, Sector request) {
        try {
            Sector update = this.sectorValidate.update(id, request);
            this.sectorRepository.save(update);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
