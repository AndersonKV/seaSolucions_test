package com.example.demo.utils;

import com.example.demo.DTO.EmploymentDTO.EmploymentUpdateDTO;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Employment;

public class EmploymentFactory {

    public Employment create(Long sectorId, String name) {
        Employment employment = new Employment();

        employment.setName(name);
        employment.setSectorId(sectorId);

        return employment;
    }

    public EmploymentUpdateDTO update(Long sectorId, String name) {
        EmploymentUpdateDTO sector = new EmploymentUpdateDTO();

        sector.setEmployment(name);
        sector.setSectorId(sectorId);

        return sector;
    }


}
