package com.example.demo.service.EmployeeService;

import com.example.demo.entities.Employee;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDeleteService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public ResponseEntity deleteById(Long id) {
        try {
            if (this.employeeRepository.findById(id).isEmpty()) {
                throw new ApiRequestException("id " + id + " não encontrado");
            }

            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }


    public ResponseEntity deleteAll() {
        try {
            this.employeeRepository.deleteAll();
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
