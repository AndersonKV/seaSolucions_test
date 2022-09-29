package com.example.demo.utils;

import com.example.demo.entities.Sector;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SectorValidate extends UtilsValid {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmploymentRepository employmentRepository;
    @Autowired
    private SectorRepository sectorRepository;

    public SectorValidate(EmployeeRepository employeeRepository, EmploymentRepository employmentRepository, SectorRepository sectorRepository) {
        super(employeeRepository, employmentRepository, sectorRepository);
    }

    public Sector update(Long id, Sector update) {
        Optional<Sector> sector = this.sectorRepository.findById(id);

        if (this.sectorRepository.findById(id).isEmpty()) {
            throw new ApiRequestException("id não encontrado");
        }

        if (!sector.get().getSectorName().equals(update.getSectorName())) {
            if (this.sectorRepository.findBySectorName(update.getSectorName()).isPresent()) {
                throw new ApiRequestException("já existe um setor com esse nome " + update.getSectorName());

            }
        }

        update.setId(id);
        return update;
    }

}
