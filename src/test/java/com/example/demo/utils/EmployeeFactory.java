package com.example.demo.utils;

import com.example.demo.DTO.EmployeeDTO.EmployeeDTO;
import com.example.demo.DTO.EmployeeDTO.EmployeeUpdateDTO;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Employment;

public class EmployeeFactory {
    public EmployeeDTO create(String name, String cpf, Long employmentId, Long sectorId) {

        EmployeeDTO employee = new EmployeeDTO();

        employee.setCPF(cpf);
        employee.setEmploymentId(employmentId);
        employee.setSectorId(sectorId);
        employee.setName(name);

        return employee;
    }

    public Employee createNotDTO(String name, String cpf, Long employmentId, Long sectorId) {

        Employee employee = new Employee();

        employee.setCPF(cpf);
        employee.setEmploymentId(employmentId);
        employee.setSectorId(sectorId);
        employee.setNameEmployee(name);

        return employee;
    }



    public EmployeeUpdateDTO update(String name, String cpf, Long employmentId, Long sectorId) {

        EmployeeUpdateDTO employee = new EmployeeUpdateDTO();

        employee.setCPF(cpf);
        employee.setEmploymentId(employmentId);
        employee.setSectorId(sectorId);
        employee.setName(name);

        return employee;
    }



}
