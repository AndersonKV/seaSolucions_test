package com.example.demo.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class EmploymentAggregationDTO {
    private String positionName;
    private List<ListEmployeeDTO> listEmployee;

    public EmploymentAggregationDTO() {

    }

    public EmploymentAggregationDTO(String positionName, List<ListEmployeeDTO> listEmployee) {
        this.positionName = positionName;
        this.listEmployee = listEmployee;
    }
}
