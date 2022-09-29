package com.example.demo.service.EmploymentService;

import com.example.demo.DTO.EmployeeDTO.EmployeeUpdateDTO;
import com.example.demo.DTO.EmploymentDTO.EmploymentUpdateDTO;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.utils.EmployeeValidate;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@AllArgsConstructor
public class EmploymentUpdateService {
    private EmployeeRepository employeeRepository;
    private EmployeeValidate employeeValidate;

    public ResponseEntity update(Long id, @Valid EmploymentUpdateDTO request) {

    }
}
