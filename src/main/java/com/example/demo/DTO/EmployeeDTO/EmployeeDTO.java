package com.example.demo.DTO.EmployeeDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class EmployeeDTO {
    //@CPF(message = "CPF inválido")
    @Size(min = 11, max = 11)
    private String CPF;

    @NotEmpty(message = "nome não pode estar vazio")
    private String name;

    @NotNull(message = "nome do cargo não pode estar vazio")
    private Long employmentId;

    @NotNull(message = "nome do setor não pode estar vazio")
    private Long sectorId;

    private String sectorName;
    private String employmentName;


    public EmployeeDTO() {
    }

    public EmployeeDTO(@Size(min = 11, max = 11) String CPF, @NotEmpty(message = "nome não pode estar vazio") String name, @NotNull(message = "nome do cargo não pode estar vazio") Long employmentId, @NotNull(message = "nome do setor não pode estar vazio") Long sectorId) {
        this.CPF = CPF;
        this.name = name;
        this.employmentId = employmentId;
        this.sectorId = sectorId;
    }
}
