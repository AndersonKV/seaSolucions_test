package com.example.demo.utils;

import com.example.demo.entities.Employment;
import com.example.demo.entities.Sector;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class EmployeeValidate {

    private EmploymentRepository employmentRepository;
    private SectorRepository sectorRepository;
    private EmploymentValidate employmentValidate;

    public Employment pass(Employment request) {

        Optional<Employment> positionExist = this.employmentRepository.findByPositionName(request.getPositionName());

        if (positionExist.isPresent()) {
            throw new ApiRequestException("já existe um cargo com esse nome: " + request.getPositionName());
        }

        Optional<Sector> sectorExist = this.sectorRepository.findBySectorName(request.getSectorName());

        if (sectorExist.isEmpty()) {
            throw new ApiRequestException("nenhum setor com esse nome foi encontrado: " + request.getSectorName());
        }

        request.setSectorId(sectorExist.get().getId());

        return request;
    }

}