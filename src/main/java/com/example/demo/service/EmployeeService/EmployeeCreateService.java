package com.example.demo.service.EmployeeService;

import com.example.demo.controllers.DTO.EmployeeDTO;
import com.example.demo.entities.Position;
import com.example.demo.entities.Sector;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.entities.Employee;
import com.example.demo.repository.PositionRepository;
import com.example.demo.repository.SectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeCreateService {
    private EmployeeRepository employeeRepository;
    private PositionRepository positionRepository;
    private SectorRepository sectorRepository;


    public Employee create(EmployeeDTO request) {
        try {
//            Optional<Employee> employeeIsRegister = this.employeeRepository.findByCPF(request.getCPF());
//
//            if (employeeIsRegister.isPresent()) {
//                throw new ApiRequestException("esse cpf esta em uso: " + request.getCPF());
//            }

            Optional<Position> positionExist = this.positionRepository.findByPositionName(request.getPositionName());

            if (positionExist.isEmpty()) {
                throw new ApiRequestException("nenhum cargo foi encontrado com esse nome: " + request.getPositionName());
            }

            Optional<Sector> sectorExist = this.sectorRepository.findBySectorName(request.getSectorName());

            if (sectorExist.isEmpty()) {
                throw new ApiRequestException("nenhum setor com esse nome foi encontrado: " + request.getSectorName());
            }

            var employee = new Employee(request.getCPF(),
                    request.getNameEmployee(),
                    positionExist.get().getId(),
                    sectorExist.get().getId());

            return this.employeeRepository.save(employee);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }

    }
}


