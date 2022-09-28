package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    private String name;

    @ManyToOne
    private Position position;

    @ManyToOne
    private Sector sector;


}
