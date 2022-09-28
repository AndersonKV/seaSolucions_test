package com.example.demo.controllers.DTO;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Setter
@Getter
public class EmployeeDTO {
    @CPF(message = "CPF inválido")
    private String CPF;
    private String name;
    private String positionName;
    private String sectorName;
    private String positionMessage;
    private String setctorMessage;
}
