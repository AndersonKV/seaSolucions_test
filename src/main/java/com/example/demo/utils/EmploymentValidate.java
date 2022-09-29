package com.example.demo.utils;

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Employment;
import com.example.demo.entities.Sector;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import jdk.jshell.execution.Util;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class EmploymentValidate {
    private EmployeeRepository employeeRepository;
    private EmploymentRepository employmentRepository;
    private SectorRepository sectorRepository;
    private UtilsValid utilsValid;


    public Employee pass(EmployeeDTO request) {

        this.utilsValid.cpfIsValid(request.getCPF());

        this.employeeHasRegisterWithCPF(request.getCPF());

        Long employmentId = this.employmentExist(request.getEmployment());

        Long sectorId = this.sectorExist(request.getSector());

        var employee = new Employee(request.getCPF(),
                request.getName(),
                employmentId,
                sectorId);

        return employee;
    }

    protected void employeeHasRegisterWithCPF(String cpf) {
        Optional<Employee> employeeIsRegister = this.employeeRepository.findByCPF(cpf);

        if (employeeIsRegister.isPresent()) {
            throw new ApiRequestException("esse cpf " + cpf + " já foi registrado");
        }
    }

    protected Long employmentExist(String name) {
        Optional<Employment> employment = this.employmentRepository.findByPositionName(name);

        if (employment.isEmpty()) {
            throw new ApiRequestException("nenhum cargo com o nome " + name + " foi encontradofoi encontrado");
        }

        return employment.get().getId();
    }

    protected Long sectorExist(String name) {
        Optional<Sector> sectorExist = this.sectorRepository.findBySectorName(name);

        if (sectorExist.isEmpty()) {
            throw new ApiRequestException("nenhum setor com o nome " + name + " foi encontrado");
        }

        return sectorExist.get().getId();

    }

}
