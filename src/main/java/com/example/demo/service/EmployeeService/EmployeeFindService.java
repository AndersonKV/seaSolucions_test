package com.example.demo.service.EmployeeService;

import com.example.demo.controllers.DTO.EmployeeDTO;
import com.example.demo.entities.Employee;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeFindService {
    private EmployeeRepository employeeRepository;
    private EmploymentRepository employmentRepository;
    private SectorRepository sectorRepository;

    public EmployeeDTO findById(Long id) {
        try {

            var findEmployee = this.employeeRepository.findById(id);

            if (findEmployee.isEmpty()) {
                throw new ApiRequestException("nenhum empregado com esse id foi encontrado: " + id);
            }

            var findPosition = this.employmentRepository.findById(findEmployee.get().getPositionId());

            if (findPosition.isEmpty()) {
                throw new ApiRequestException("nenhum ew333mpregado com esse id foi encontrado: " + findEmployee.get().getPositionId());
            }

            var findSector = this.sectorRepository.findById(findEmployee.get().getSectorId());

            if (findSector.isEmpty()) {
                throw new ApiRequestException("nenhum ew333mpregado com esse id foi encontrado: " + findEmployee.get().getSectorId());
            }


            var getEmployee = new EmployeeDTO(
                    findEmployee.get().getCPF(),
                    findEmployee.get().getNameEmployee(),
                    findPosition.get().getPositionName(),
                    findSector.get().getSectorName());


            return getEmployee;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    public EmployeeDTO findByInfoAboutEmployee(Long id) {
        try {
            Optional<Employee> findEmployee = this.employeeRepository.findById(id);

            if (findEmployee.isEmpty()) {
                throw new ApiRequestException("nenhum empregado com esse id foi encontrado: " + id);
            }

            var getPosition = this.employmentRepository.findById(findEmployee.get().getPositionId());

            if (getPosition.isEmpty()) {
                throw new ApiRequestException("não foi possivel recuperar o cargo");
            }

            var getSector = this.sectorRepository.findById(findEmployee.get().getSectorId());

            if (getSector.isEmpty()) {
                throw new ApiRequestException("não foi possivel recuperar o setor");
            }

            var employeeDTO = new EmployeeDTO(
                    findEmployee.get().getCPF(),
                    findEmployee.get().getNameEmployee(),
                    getPosition.get().getPositionName(),
                    getSector.get().getSectorName()
            );


            return employeeDTO;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    public List<Employee> findByName(String name) {
        try {
            List<Employee> findByName = this.employeeRepository.findByNameEmployee(name);

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
