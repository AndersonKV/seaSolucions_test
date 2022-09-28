package com.example.demo.service.SectorService;

import com.example.demo.entities.Employee;
import com.example.demo.entities.Sector;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SectorCreateService {
    @Autowired
    private SectorRepository sectorRepository;

    public Sector create(Sector request) {
        try {
            Optional<Sector> sectorExist = this.sectorRepository.findByName(request.getName());

            if (sectorExist.isPresent()) {
                throw new ApiRequestException("já existe um setor com esse nome: " + request.getName());
            }

             return this.sectorRepository.save(request);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }

    }
}
