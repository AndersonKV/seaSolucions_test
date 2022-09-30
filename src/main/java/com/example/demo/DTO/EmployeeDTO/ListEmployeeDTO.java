package com.example.demo.DTO.EmployeeDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListEmployeeDTO {
    private Long id;
    private String CPF;
    private String nameEmployee;

    public ListEmployeeDTO() {

    }

    public ListEmployeeDTO(Long id, String CPF, String nameEmployee) {
        this.id = id;
        this.CPF = CPF;
        this.nameEmployee = nameEmployee;
    }
}
