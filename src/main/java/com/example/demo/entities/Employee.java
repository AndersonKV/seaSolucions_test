package com.example.demo.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tb_employee")
public class Employee {
    @Id
    @SequenceGenerator(name = "employees_sequence", sequenceName = "employees_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_sequence")
    private Long id;
    private String cpf;
    private String name;


    @ManyToOne
    @JoinColumn(name = "id_position")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "id_sector")
    private Sector sector;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
