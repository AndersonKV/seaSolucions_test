package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@Entity
@Table(name = "tb_employee")
public class Employee {
    @Id
    @SequenceGenerator(name = "employees_sequence", sequenceName = "employees_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_sequence")
    private Long id;

    @CPF(message = "CPF inválido")
    private String CPF;

    @NotEmpty(message = "nome não pode estar vazio")
    private String nameEmployee;

    private Long positionId;

    private Long sectorId;

//    private String positionName;
//    private String sectorName;
    public Employee() {
    }

    public Employee(String CPF, String name, Long positionId, Long sectorId) {
        this.CPF = CPF;
        this.nameEmployee = name;
        this.positionId = positionId;
        this.sectorId = sectorId;
    }
}
