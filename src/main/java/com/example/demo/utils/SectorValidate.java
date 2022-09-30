package com.example.demo.utils;

import com.example.demo.DTO.EmployeeDTO.EmploymentPopulateDTO;
import com.example.demo.DTO.EmployeeDTO.ListEmployeeDTO;
import com.example.demo.DTO.SectorDTO;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Employment;
import com.example.demo.entities.Sector;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<SectorDTO> populateSectorListById(Long id) {
        Sector getSector = this.sectorExist(id);

        List<SectorDTO> listShouldReturnInEnding = new ArrayList<>();

        List<Employment> getEmployments = this.employmentRepository.findBySectorId(getSector.getId());

        List<EmploymentPopulateDTO> tempListEmployment = new ArrayList<>();

        getEmployments.stream().forEach(employment -> {
            List<ListEmployeeDTO> tempListEmployees = new ArrayList<>();

            List<Employee> getAllEmployees = this.employeeRepository.findByEmploymentIdAndSectorId(employment.getId(), employment.getSectorId());

            getAllEmployees.stream().forEach(employee -> {
                ListEmployeeDTO tempEmployee = new ListEmployeeDTO(employee.getId(), employee.getCpf(), employee.getNameEmployee());

                tempListEmployees.add(tempEmployee);
            });

            var tempEmployee = new EmploymentPopulateDTO(employment.getId(), employment.getName(), tempListEmployees);

            tempListEmployment.add(tempEmployee);
        });

        SectorDTO tempSector = new SectorDTO(getSector.getId(), getSector.getSectorName(), tempListEmployment);

        listShouldReturnInEnding.add(tempSector);

        return listShouldReturnInEnding;
    }

    public List<SectorDTO> populateSectorListWithEmploymentAndEmployee() {
        List<Sector> listSectors = this.sectorRepository.findAll();

        List<SectorDTO> listShouldReturnInEnding = new ArrayList<>();

        listSectors.stream().forEach(sector -> {

            List<Employment> listEmployments = this.employmentRepository.findBySectorId(sector.getId());

            List<EmploymentPopulateDTO> tempListEmployment = new ArrayList<>();

            listEmployments.stream().forEach(employment -> {
                List<ListEmployeeDTO> tempListEmployees = new ArrayList<>();

                List<Employee> getAllEmployees = this.employeeRepository.findByEmploymentIdAndSectorId(employment.getId(), employment.getSectorId());

                getAllEmployees.stream().forEach(employee -> {
                    ListEmployeeDTO tempEmployee = new ListEmployeeDTO(employee.getId(), employee.getCpf(), employee.getNameEmployee());

                    tempListEmployees.add(tempEmployee);
                });

                var tempEmployee = new EmploymentPopulateDTO(employment.getId(), employment.getName(), tempListEmployees);

                tempListEmployment.add(tempEmployee);
            });

            SectorDTO tempSector = new SectorDTO(sector.getId(), sector.getSectorName(), tempListEmployment);

            listShouldReturnInEnding.add(tempSector);
        });

        return listShouldReturnInEnding;
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
