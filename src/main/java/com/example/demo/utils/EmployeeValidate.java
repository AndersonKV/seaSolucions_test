package com.example.demo.utils;

import com.example.demo.DTO.EmployeeDTO.EmployeeDTO;
import com.example.demo.DTO.EmployeeDTO.EmployeeFindDTO;
import com.example.demo.DTO.EmployeeDTO.EmployeeUpdateDTO;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Employment;
import com.example.demo.entities.Sector;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EmployeeValidate extends UtilsValid {
    private EmployeeRepository employeeRepository;

    public EmployeeValidate(EmployeeRepository employeeRepository, EmploymentRepository employmentRepository, SectorRepository sectorRepository) {
        super(employeeRepository, employmentRepository, sectorRepository);
    }

    public Employee create(EmployeeDTO request) {
        this.cpfIsValid(request.getCPF());

        this.employeeHasRegisterWithCPF(request.getCPF());

        this.employmentExist(request.getEmploymentId());

        this.sectorExist(request.getSectorId());

        var employee = new Employee();

        employee.setNameEmployee(request.getName());
        employee.setCPF(request.getCPF());
        employee.setSectorId(request.getSectorId());
        employee.setEmploymentId(request.getEmploymentId());

        return employee;
    }


    public Employee update(Long id, EmployeeUpdateDTO employeeUpdate) {
        Employee currentEmployee = this.employeeExist(id);

        this.checkIsCPFUpdateAndExist(currentEmployee.getCPF(), employeeUpdate.getCPF());

        Employment employment = this.employmentExist(employeeUpdate.getEmploymentId());

        Employee employee = new Employee();

        employee.setId(id);
        employee.setNameEmployee(employeeUpdate.getName());
        employee.setCPF(employeeUpdate.getCPF());
        employee.setEmploymentId(employment.getId());
        employee.setSectorId(employment.getSectorId());

        return employee;
    }

    public EmployeeFindDTO getAllInfoById(Long id) {

        Employee employee = this.checkIsUserExist(id);

        Employment employment = this.getEmploymentById(employee.getEmploymentId());

        Sector sector = this.getSectorById(employee.getSectorId());

        var employeeFindDTO = new EmployeeFindDTO();

        employeeFindDTO.setCPF(employee.getCPF());
        employeeFindDTO.setName(employee.getNameEmployee());
        employeeFindDTO.setSectorName(sector.getSectorName());
        employeeFindDTO.setEmploymentName(employment.getName());

        return employeeFindDTO;


    }

}