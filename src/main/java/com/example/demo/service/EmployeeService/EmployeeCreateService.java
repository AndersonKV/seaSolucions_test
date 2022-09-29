package com.example.demo.service.EmployeeService;

import com.example.demo.DTO.EmployeeDTO.EmployeeDTO;
import com.example.demo.entities.Employee;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.utils.EmployeeValidate;
import com.example.demo.utils.EmploymentValidate;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeCreateService {
    private EmployeeRepository employeeRepository;
    private EmployeeValidate employeeValidate;


    public ResponseEntity create(EmployeeDTO request) {
        try {
            Employee employee = this.employeeValidate.create(request);

            var createdEmployee = this.employeeRepository.save(employee);

            return new ResponseEntity(createdEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }

    }


}


