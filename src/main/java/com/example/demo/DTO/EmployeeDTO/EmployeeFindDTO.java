package com.example.demo.DTO.EmployeeDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class EmployeeFindDTO {
    private String CPF;
    private String name;
    private String sectorName;
    private String employmentName;


}
