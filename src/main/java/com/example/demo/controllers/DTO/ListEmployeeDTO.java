package com.example.demo.controllers.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListEmployeeDTO {
    private String CPF;
    private String nameEmployee;

    public ListEmployeeDTO( ) {

    }
    public ListEmployeeDTO(String CPF, String nameEmployee) {
        this.CPF = CPF;
        this.nameEmployee = nameEmployee;
    }
}
