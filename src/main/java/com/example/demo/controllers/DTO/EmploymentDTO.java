package com.example.demo.controllers.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class EmploymentDTO {
    private String positionName;
    private String sectorName;

    private List<ListEmployeeDTO> employeeList;

    public EmploymentDTO() {

    }

    public EmploymentDTO(String positionName, String sectorName) {
        this.positionName = positionName;
        this.sectorName = sectorName;
    }




    public EmploymentDTO(String positionName, String sectorName, List<ListEmployeeDTO> employeeList) {
        this.positionName = positionName;
        this.sectorName = sectorName;
        this.employeeList = employeeList;
    }
}
