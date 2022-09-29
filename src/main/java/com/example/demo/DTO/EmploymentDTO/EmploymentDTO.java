package com.example.demo.DTO.EmploymentDTO;

import com.example.demo.DTO.EmployeeDTO.ListEmployeeDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
public class EmploymentDTO {
    @NotEmpty(message = "nome do cargo não pode estar vazio")
    private String employment;
    @NotNull(message = "id do setor não pode estar vazio")
    private String sectorId;

    private List<ListEmployeeDTO> employeeList;

    public EmploymentDTO() {

    }

    public EmploymentDTO(String employment, String sectorId) {
        this.employment = employment;
        this.sectorId = sectorId;
    }




    public EmploymentDTO(String employment, String sectorId, List<ListEmployeeDTO> employeeList) {
        this.employment = employment;
        this.sectorId = sectorId;
        this.employeeList = employeeList;
    }
}
