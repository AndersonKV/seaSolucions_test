package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "tb_sector")
public class Sector {
    @Id
    @SequenceGenerator(name = "tb_sectors", sequenceName = "tb_sectors", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_sectors")
    private Long id;
    @NotEmpty(message = "nome do setor não pode estar vazio")
    private String sectorName;



}


