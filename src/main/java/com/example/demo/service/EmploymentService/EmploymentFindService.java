package com.example.demo.service.EmploymentService;

import com.example.demo.controllers.DTO.ListEmployeeDTO;
import com.example.demo.controllers.DTO.EmploymentDTO;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Employment;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class EmploymentFindService {
    private EmployeeRepository employeeRepository;
    private EmploymentRepository employmentRepository;
    private SectorRepository sectorRepository;


    public List<Employment> findAll() {
        return this.employmentRepository.findAll();
    }

    public EmploymentDTO findByPositionName(String name) {
        try {
            Optional<Employment> positionExist = this.employmentRepository.findByPositionName(name);

            if (positionExist.isEmpty()) {
                throw new ApiRequestException("nenhum cargo com esse nome foi encontrado: " + name);
            }

            List<Employee> getAllEmployees = this.employeeRepository.findByPositionIdAndSectorId(positionExist.get().getId(), positionExist.get().getSectorId());

            EmploymentDTO employmentDTO = new EmploymentDTO(
                    positionExist.get().getPositionName(),
                    positionExist.get().getSectorName()
                    );

            List<ListEmployeeDTO> ArrayListEmployees = new ArrayList<>();

            getAllEmployees.stream().forEach(a -> {
                ArrayListEmployees.add(new ListEmployeeDTO(a.getCPF(), a.getNameEmployee()));
            });

            employmentDTO.setEmployeeList(ArrayListEmployees);

            return employmentDTO;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
