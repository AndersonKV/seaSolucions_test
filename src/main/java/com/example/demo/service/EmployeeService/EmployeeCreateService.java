package com.example.demo.service.EmployeeService;

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Employment;
import com.example.demo.entities.Sector;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import com.example.demo.utils.EmploymentValidate;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class EmployeeCreateService {
    private EmployeeRepository employeeRepository;
    private EmploymentRepository employmentRepository;
    private SectorRepository sectorRepository;
    private EmploymentValidate employmentValidate;


    public ResponseEntity create(EmployeeDTO request) {
        try {
            Employee employee = this.employmentValidate.pass(request);

            var createdEmployee = this.employeeRepository.save(employee);
            return new ResponseEntity(createdEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }

    }


}


