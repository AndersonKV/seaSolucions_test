package com.example.demo.service.PositionService;

import com.example.demo.controllers.DTO.ListEmployeeDTO;
import com.example.demo.controllers.DTO.PositionDTO;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Position;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PositionRepository;
import com.example.demo.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PositionFindService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private SectorRepository sectorRepository;


    public List<Position> findAll() {
        return this.positionRepository.findAll();
    }

    public PositionDTO findByPositionName(String name) {
        try {
            Optional<Position> positionExist = this.positionRepository.findByPositionName(name);

            if (positionExist.isEmpty()) {
                throw new ApiRequestException("nenhum cargo com esse nome foi encontrado: " + name);
            }

            List<Employee> getAllEmployees = this.employeeRepository.findByPositionIdAndSectorId(positionExist.get().getId(), positionExist.get().getSectorId());

            PositionDTO positionDTO = new PositionDTO(
                    positionExist.get().getPositionName(),
                    positionExist.get().getSectorName()
                    );

            List<ListEmployeeDTO> ArrayListEmployees = new ArrayList<>();

            getAllEmployees.stream().forEach(a -> {
                ArrayListEmployees.add(new ListEmployeeDTO(a.getCPF(), a.getNameEmployee()));
            });

            positionDTO.setEmployeeList(ArrayListEmployees);

            return positionDTO;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
