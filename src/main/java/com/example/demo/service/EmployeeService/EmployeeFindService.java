package com.example.demo.service.EmployeeService;

import com.example.demo.DTO.EmployeeDTO.EmployeeFindDTO;
import com.example.demo.entities.Employee;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.utils.EmployeeValidate;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeFindService {
    private EmployeeRepository employeeRepository;
    private EmployeeValidate employeeValidate;

    public ResponseEntity findAll() {
        try {
            List<Employee> list = this.employeeRepository.findAll();
            return new ResponseEntity(list, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }


    public ResponseEntity findById(Long id) {
        try {
            EmployeeFindDTO employeeDTO = this.employeeValidate.getAllInfoById(id);
            return new ResponseEntity(employeeDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    public ResponseEntity findByName(String name) {
        try {
            List<Employee> findByName = this.employeeRepository.findByNameEmployee(name);

            if (findByName.isEmpty()) {
                throw new ApiRequestException("nenhum empregado com esse nome foi encontrado: " + name);
            }

            return new ResponseEntity(findByName, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    public ResponseEntity findByCpf(String cpf) {
        try {
            Optional<Employee> find = this.employeeRepository.findByCpf(cpf);

            if (find.isEmpty()) {
                throw new ApiRequestException("nenhum cpf com esses numero foi encontrado: " + cpf);
            }

            return new ResponseEntity(find.get(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
