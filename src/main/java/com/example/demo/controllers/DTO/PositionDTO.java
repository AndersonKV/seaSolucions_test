package com.example.demo.controllers.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PositionDTO {
    private String positionName;
    private String sectorName;

    private List<ListEmployeeDTO> employeeList;

    public PositionDTO() {

    }

    public PositionDTO(String positionName, String sectorName) {
        this.positionName = positionName;
        this.sectorName = sectorName;
    }
}
