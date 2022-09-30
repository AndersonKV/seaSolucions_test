package com.example.demo.utils;

import com.example.demo.DTO.EmployeeDTO.ListEmployeeDTO;
import com.example.demo.DTO.EmploymentDTO.EmploymentPopulateDTO;
import com.example.demo.DTO.EmploymentDTO.EmploymentUpdateDTO;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Employment;
import com.example.demo.entities.Sector;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


    public Employment update(Long id, EmploymentUpdateDTO update) {
        var currentEmployment = this.employmentExist(id);

        this.employmentNameUpdate(currentEmployment.getName(), update.getEmployment());

        this.employmentUpdateSectorExist(currentEmployment.getSectorId(), update.getSectorId());

        Employment employment = new Employment();

        employment.setId(id);
        employment.setName(update.getEmployment());
        employment.setSectorId(update.getSectorId());

        return employment;
    }


    public EmploymentPopulateDTO getEmploymentAndUsers(String name) {
        Employment employment = this.throwErrorIfEmploymentNameNotFound(name);

        Sector sector = this.sectorExist(employment.getSectorId());

        List<Employee> listEmployee = this.employeeRepository.findByEmploymentIdAndSectorId(employment.getId(), employment.getSectorId());

        EmploymentPopulateDTO employmentPopulateDTO = new EmploymentPopulateDTO(
                employment.getId(),
                employment.getName(),
                sector.getSectorName()
        );

        List<ListEmployeeDTO> listEmployeeDTO = new ArrayList<>();

        listEmployee.stream().forEach(employee -> {
            listEmployeeDTO.add(new ListEmployeeDTO(employee.getId(), employee.getCpf(), employee.getNameEmployee()));
        });

        employmentPopulateDTO.setEmployeeList(listEmployeeDTO);

        return employmentPopulateDTO;
    }


}
