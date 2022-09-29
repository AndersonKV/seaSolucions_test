package com.example.demo.service.EmploymentService;

import com.example.demo.entities.Employment;
import com.example.demo.entities.Sector;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmploymentCreateService {
    private EmploymentRepository employmentRepository;
    private SectorRepository sectorRepository;

    public Employment create(Employment request) {
        try {

            Optional<Employment> positionExist = this.employmentRepository.findByPositionName(request.getPositionName());

            if (positionExist.isPresent()) {
                throw new ApiRequestException("já existe um cargo com esse nome: " + request.getPositionName());
            }

            Optional<Sector> sectorExist = this.sectorRepository.findBySectorName(request.getSectorName());

            if (sectorExist.isEmpty()) {
                throw new ApiRequestException("nenhum setor com esse nome foi encontrado: " + request.getSectorName());
            }

            request.setSectorId(sectorExist.get().getId());

            return this.employmentRepository.save(request);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }

    }
}
