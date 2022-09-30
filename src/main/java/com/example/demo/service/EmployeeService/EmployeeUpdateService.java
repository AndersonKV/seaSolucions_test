package com.example.demo.service.EmployeeService;

import com.example.demo.DTO.EmployeeDTO.EmployeeUpdateDTO;
import com.example.demo.entities.Employee;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import com.example.demo.utils.EmployeeValidate;
import com.example.demo.utils.EmploymentValidate;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeUpdateService {
    private EmployeeRepository employeeRepository;
    private EmployeeValidate employeeValidate;

    public ResponseEntity update(Long id, EmployeeUpdateDTO request) {

        try {
            Employee update = this.employeeValidate.update(id, request);

            var employee =this.employeeRepository.save(update);

            return new ResponseEntity(employee, HttpStatus.CREATED);
        }catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }

    }
}
