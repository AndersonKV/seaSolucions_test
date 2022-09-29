package com.example.demo.service.EmploymentService;

import com.example.demo.entities.Employment;
import com.example.demo.entities.Sector;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import com.example.demo.utils.EmploymentValidate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmploymentCreateService {
    private EmploymentRepository employmentRepository;
    private SectorRepository sectorRepository;
    private EmploymentValidate employmentValidate;

    public Employment create(Employment request) {
        try {
            //Employment employment = this.employmentValidate.pass(request);
            return this.employmentRepository.save(request);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }

    }
}
