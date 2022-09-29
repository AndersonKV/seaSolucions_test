package com.example.demo.DTO.EmployeeDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class EmploymentAggregationDTO {
    private String employment;
    private List<ListEmployeeDTO> listEmployee;

    public EmploymentAggregationDTO() {

    }

    public EmploymentAggregationDTO(String employment, List<ListEmployeeDTO> listEmployee) {
        this.employment = employment;
        this.listEmployee = listEmployee;
    }
}
