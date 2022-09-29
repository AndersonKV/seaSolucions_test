package com.example.demo.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
public class EmployeeDTO {
    //@CPF(message = "CPF inválido")
    @Size(min = 11, max = 11)
    private String CPF;

    @NotEmpty(message = "nome não pode estar vazio")
    private String name;

    @NotEmpty(message = "nome do cargo não pode estar vazio")
    private String employment;

    @NotEmpty(message = "nome do setor não pode estar vazio")
    private String sector;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String CPF, String name, String employment, String sector) {
        this.CPF = CPF;
        this.name = name;
        this.employment = employment;
        this.sector = sector;
    }
}
