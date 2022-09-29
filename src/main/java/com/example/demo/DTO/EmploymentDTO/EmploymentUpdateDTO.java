package com.example.demo.DTO.EmploymentDTO;

import com.example.demo.DTO.EmployeeDTO.ListEmployeeDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
public class EmploymentUpdateDTO {
    @NotEmpty(message = "nome do cargo não pode estar vazio")
    private String employment;

    @NotNull(message = "id do setor não pode estar vazio")
    private Long sectorId;

}
