package com.example.demo.service.EmployeeService;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmployeeCreateService {
    @Autowired
    private EmployeeRepository employeeRepository;


    public Employee create(Employee request) {
        try {
            Optional<Employee> employeeIsRegister = this.employeeRepository.findByCPF(request.getCPF());

            if (employeeIsRegister.isPresent()) {
                throw new ApiRequestException("esse cpf esta em uso: " + request.getCPF());
            }

            return this.employeeRepository.save(request);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }

    }
}


