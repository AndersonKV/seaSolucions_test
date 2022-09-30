package com.example.demo.service.EmploymentService;

import com.example.demo.DTO.EmployeeDTO.EmployeeUpdateDTO;
import com.example.demo.DTO.EmploymentDTO.EmploymentUpdateDTO;
import com.example.demo.entities.Employment;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.utils.EmployeeValidate;
import com.example.demo.utils.EmploymentValidate;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@AllArgsConstructor
public class EmploymentUpdateService {
    private EmploymentRepository employmentRepository;
    private EmploymentValidate employmentValidate;

    public ResponseEntity update(Long id, EmploymentUpdateDTO request) {
        try {
            var update = this.employmentValidate.update(id, request);
            this.employmentRepository.save(update);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }

    }
}
