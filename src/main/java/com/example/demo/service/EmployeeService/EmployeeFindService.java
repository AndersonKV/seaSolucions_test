package com.example.demo.service.EmployeeService;

import com.example.demo.controllers.DTO.EmployeeDTO;
import com.example.demo.entities.Employee;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PositionRepository;
import com.example.demo.repository.SectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeFindService {
    private EmployeeRepository employeeRepository;
    private PositionRepository positionRepository;
    private SectorRepository sectorRepository;

    public Employee findById(Long id) {
        try {

            Optional<Employee> find = this.employeeRepository.findById(id);

            if (find.isEmpty()) {
                throw new ApiRequestException("nenhum empregado com esse id foi encontrado: " + id);
            }

            return find.get();
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    public EmployeeDTO findByInfoAboutEmployee(Long id) {
        try {
            var employeeDTO = new EmployeeDTO();

            Optional<Employee> find = this.employeeRepository.findById(id);

            if (find.isEmpty()) {
                throw new ApiRequestException("nenhum empregado com esse id foi encontrado: " + id);
            }

            var getPosition = this.positionRepository.findById(find.get().getPositionId());

            if (getPosition.isEmpty()) {
                employeeDTO.setPositionMessage("não foi possivel recuperar as informações sobre o cargo");
            } else  {
                employeeDTO.setPositionName(getPosition.get().getName());
            }

            var getSector = this.sectorRepository.findById(find.get().getSectorId());

            if (getSector.isEmpty()) {
                employeeDTO.setSetctorMessage("não foi possivel recuperar as informações sobre o setor");
            } else {
                employeeDTO.setPositionName(getSector.get().getName());
            }

            employeeDTO.setCPF(find.get().getCPF());
            employeeDTO.setName(find.get().getName());

            return employeeDTO;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    public List<Employee> findByName(String name) {
        try {
            List<Employee> findByName = this.employeeRepository.findByName(name);

            if (findByName.isEmpty()) {
                throw new ApiRequestException("nenhum empregado com esse nome foi encontrado: " + name);
            }

            return findByName;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    public Employee findByCpf(String cpf) {
        try {
            Optional<Employee> find = this.employeeRepository.findByCPF(cpf);

            if (find.isEmpty()) {
                throw new ApiRequestException("nenhum cpf com esses numero foi encontrado: " + cpf);
            }

            return find.get();
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
