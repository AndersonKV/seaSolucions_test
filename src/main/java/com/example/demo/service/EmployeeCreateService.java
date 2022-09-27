package com.example.demo.service;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeCreateService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee create(Employee request) {

        try {
            return this.employeeRepository.save(request);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }

     }
}
