package com.example.demo.controllers.DTO;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class EmployeeDTO {
    //@CPF(message = "CPF inválido")
    private String CPF;

    @NotEmpty(message = "nome não pode estar vazio")
    private String nameEmployee;

    @NotEmpty(message = "nome do cargo não pode estar vazio")
    private String positionName;

    @NotEmpty(message = "nome do setor não pode estar vazio")
    private String sectorName;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String CPF, String nameEmployee, String positionName, String sectorName) {
        this.CPF = CPF;
        this.nameEmployee = nameEmployee;
        this.positionName = positionName;
        this.sectorName = sectorName;
    }
}
