package com.example.demo.DTO.EmployeeDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class EmployeeUpdateDTO {

    @Size(min = 11, max = 11)
    private String CPF;

    @NotEmpty(message = "nome não pode estar vazio")
    private String name;

    @NotNull(message = "id do cargo não pode estar vazio")
    private Long employmentId;

    //@NotEmpty(message = "id do setor não pode estar vazio")
    private Long sectorId;

}
