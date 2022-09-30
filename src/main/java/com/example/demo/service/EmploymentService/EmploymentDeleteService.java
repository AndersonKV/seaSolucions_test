package com.example.demo.service.EmploymentService;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmploymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmploymentDeleteService {
    @Autowired
    private EmploymentRepository employmentRepository;

    public ResponseEntity deleteById(Long id) {
        try {
            if (this.employmentRepository.findById(id).isEmpty()) {
                throw new ApiRequestException("id " + id + " não encontrado");
            }
            this.employmentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }


    public ResponseEntity deleteAll( ) {
        try {
            this.employmentRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
