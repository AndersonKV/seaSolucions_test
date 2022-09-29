package com.example.demo.service.SectorService;

import com.example.demo.DTO.EmployeeDTO.ListEmployeeDTO;
import com.example.demo.DTO.EmployeeDTO.EmploymentPopulateDTO;
import com.example.demo.DTO.SectorDTO;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Employment;
import com.example.demo.entities.Sector;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import com.example.demo.utils.SectorValidate;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SectorFindService {
    private EmployeeRepository employeeRepository;
    private EmploymentRepository employmentRepository;
    private SectorRepository sectorRepository;
    private SectorValidate sectorValidate;


    public ResponseEntity findAll() {
        try {
            List<Sector> listSectors = this.sectorRepository.findAll();

            List<SectorDTO> listShouldReturnInEnding = new ArrayList<>();

            listSectors.stream().forEach(sector -> {

                List<Employment> listEmployments = this.employmentRepository.findBySectorId(sector.getId());

                List<EmploymentPopulateDTO> tempListEmployment = new ArrayList<>();

                listEmployments.stream().forEach(p -> {
                    List<ListEmployeeDTO> tempListEmployees = new ArrayList<>();

                    List<Employee> getAllEmployees = this.employeeRepository.findByEmploymentIdAndSectorId(p.getId(), p.getSectorId());

                    getAllEmployees.stream().forEach(employee -> {
                        ListEmployeeDTO tempEmployee = new ListEmployeeDTO(employee.getCPF(), employee.getNameEmployee());

                        tempListEmployees.add(tempEmployee);
                    });

                    var tempEmployee = new EmploymentPopulateDTO(p.getName(), tempListEmployees);

                    tempListEmployment.add(tempEmployee);
                });

                SectorDTO tempSector = new SectorDTO(sector.getId(), sector.getSectorName(), tempListEmployment);

                listShouldReturnInEnding.add(tempSector);
            });

            return new ResponseEntity(listShouldReturnInEnding, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }

    }
}
