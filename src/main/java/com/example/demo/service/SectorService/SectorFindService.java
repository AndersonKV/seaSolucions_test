package com.example.demo.service.SectorService;

import com.example.demo.DTO.EmployeeDTO.ListEmployeeDTO;
import com.example.demo.DTO.EmployeeDTO.EmploymentPopulateDTO;
import com.example.demo.DTO.SectorDTO;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Employment;
import com.example.demo.entities.Sector;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import com.example.demo.utils.SectorValidate;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SectorFindService {
    private EmployeeRepository employeeRepository;
    private EmploymentRepository employmentRepository;
    private SectorRepository sectorRepository;
    private SectorValidate sectorValidate;


    public ResponseEntity findById(Long id) {
        try {
            List<SectorDTO> list = this.sectorValidate.populateSectorListById(id);
            return new ResponseEntity(list, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }

    }

    public ResponseEntity findAll() {
        try {
            List<SectorDTO> list = this.sectorValidate.populateSectorListWithEmploymentAndEmployee();
            return new ResponseEntity(list, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }

    }
}
