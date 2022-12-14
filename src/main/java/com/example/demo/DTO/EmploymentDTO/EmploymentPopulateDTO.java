package com.example.demo.DTO.EmploymentDTO;

import com.example.demo.DTO.EmployeeDTO.ListEmployeeDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class EmploymentPopulateDTO {
    private Long id;
    private String employment;
    private String sectorName;


    private List<ListEmployeeDTO> employeeList;

    public EmploymentPopulateDTO() {

    }

    public EmploymentPopulateDTO(Long id, String employment, String sectorName) {
        this.employment = employment;
        this.sectorName = sectorName;
        this.id = id;
    }
}
