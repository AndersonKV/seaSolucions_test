package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
@Table(name = "tb_employee")
public class Employee {
    @Id
    @SequenceGenerator(name = "employees_sequence", sequenceName = "employees_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_sequence")
    private Long id;

    //@CPF(message = "CPF inválido")
    private String cpf;

    @NotEmpty(message = "nome não pode estar vazio")
    private String nameEmployee;

    private Long employmentId;

    private Long sectorId;


    public Employee() {
    }

    public Employee(String cpf, String name, Long positionId, Long sectorId) {
        this.cpf = cpf;
        this.nameEmployee = name;
        this.employmentId = positionId;
        this.sectorId = sectorId;
    }
}
