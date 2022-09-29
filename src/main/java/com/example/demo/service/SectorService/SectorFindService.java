package com.example.demo.service.SectorService;

import com.example.demo.controllers.DTO.ListEmployeeDTO;
import com.example.demo.controllers.DTO.EmploymentAggregationDTO;
import com.example.demo.controllers.DTO.SectorDTO;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Employment;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SectorFindService {
    private EmployeeRepository employeeRepository;
    private EmploymentRepository employmentRepository;
    private SectorRepository sectorRepository;


    public List<SectorDTO> findAll() {
        var findAllSectors = this.sectorRepository.findAll();

        List<SectorDTO> list = new ArrayList<>();

        //pega todos os setores
        findAllSectors.stream().forEach(sector -> {

            List<Employment> getAllEmployments = this.employmentRepository.findBySectorId(sector.getId());

            List<EmploymentAggregationDTO> tempListPositions = new ArrayList<>();

            //pega todos os cargos
            getAllEmployments.stream().forEach(p -> {
                List<ListEmployeeDTO> tempListEmployees = new ArrayList<>();

                List<Employee> getAllEmployees = this.employeeRepository.findByPositionIdAndSectorId(p.getId(), p.getSectorId());

                //pega todos os empregados
                getAllEmployees.stream().forEach(employee -> {
                    ListEmployeeDTO tempEmployee = new ListEmployeeDTO(employee.getCPF(), employee.getNameEmployee());

                    tempListEmployees.add(tempEmployee);
                });

                var tempPosition = new EmploymentAggregationDTO(p.getPositionName(), tempListEmployees);

                tempListPositions.add(tempPosition);
            });

            SectorDTO tempSector = new SectorDTO(sector.getId(), sector.getSectorName(), tempListPositions);

            list.add(tempSector);
        });

        return list;
    }
}
