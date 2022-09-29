package com.example.demo.utils;

import com.example.demo.DTO.EmployeeDTO.ListEmployeeDTO;
import com.example.demo.DTO.EmploymentDTO.EmploymentPopulateDTO;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Employment;
import com.example.demo.entities.Sector;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmploymentValidate extends UtilsValid {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmploymentRepository employmentRepository;

    public EmploymentValidate(EmployeeRepository employeeRepository, EmploymentRepository employmentRepository, SectorRepository sectorRepository) {
        super(employeeRepository, employmentRepository, sectorRepository);
    }

    public Employment create(Employment request) {

        this.throwErrorIfEmploymentHasFound(request.getName());

        this.throwErrorIfSectorNotFound(request.getSectorId());

        return request;
    }

    public EmploymentPopulateDTO getEmploymentAndUsers(String name) {
        Employment employment = this.throwErrorIfEmploymentNameNotFound(name);

        Sector sector = this.sectorExist(employment.getSectorId());

        List<Employee> listEmployee = this.employeeRepository.findByEmploymentIdAndSectorId(employment.getId(), employment.getSectorId());

        EmploymentPopulateDTO employmentPopulateDTO = new EmploymentPopulateDTO(
                employment.getName(),
                sector.getSectorName()
        );

        List<ListEmployeeDTO> listEmployeeDTO = new ArrayList<>();

        listEmployee.stream().forEach(a -> {
            listEmployeeDTO.add(new ListEmployeeDTO(a.getCPF(), a.getNameEmployee()));
        });

        employmentPopulateDTO.setEmployeeList(listEmployeeDTO);

        return employmentPopulateDTO;
    }


}
