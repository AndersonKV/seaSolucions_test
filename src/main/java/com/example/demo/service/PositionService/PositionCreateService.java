package com.example.demo.service.PositionService;

import com.example.demo.entities.Position;
import com.example.demo.entities.Sector;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.PositionRepository;
import com.example.demo.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PositionCreateService {
    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private SectorRepository sectorRepository;

    public Position create(Position request) {
        try {

            Optional<Position> positionExist = this.positionRepository.findByPositionName(request.getPositionName());

            if (positionExist.isPresent()) {
                throw new ApiRequestException("já existe um cargo com esse nome: " + request.getPositionName());
            }

            Optional<Sector> sectorExist = this.sectorRepository.findBySectorName(request.getSectorName());

            if (sectorExist.isEmpty()) {
                throw new ApiRequestException("nenhum setor com esse nome foi encontrado: " + request.getSectorName());
            }

            request.setSectorId(sectorExist.get().getId());
            return this.positionRepository.save(request);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }

    }
}
