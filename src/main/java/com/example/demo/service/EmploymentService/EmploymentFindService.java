package com.example.demo.service.EmploymentService;

import com.example.demo.DTO.EmploymentDTO.EmploymentPopulateDTO;
import com.example.demo.entities.Employment;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import com.example.demo.utils.EmploymentValidate;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class EmploymentFindService {
    private EmployeeRepository employeeRepository;
    private EmploymentRepository employmentRepository;
    private SectorRepository sectorRepository;
    private EmploymentValidate employmentValidate;

    public ResponseEntity findAll() {
        return new ResponseEntity(this.employmentRepository.findAll(), HttpStatus.ACCEPTED);
    }

    public ResponseEntity findEmploymentByName(String name) {
        try {
            EmploymentPopulateDTO employmentPopulateDTO = this.employmentValidate.getEmploymentAndUsers(name);

            return new ResponseEntity(employmentPopulateDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
