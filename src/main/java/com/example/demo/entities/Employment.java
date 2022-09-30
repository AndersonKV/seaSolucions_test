package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Getter
@Setter
@Entity
@Table(name = "tb_employment")
public class Employment {
    @Id
    @SequenceGenerator(name = "employments_sequence", sequenceName = "employment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employment_sequence")
    private Long id;

    @NotEmpty(message = "nome do cargo não pode estar vazio")
    private String name;

    @NotNull(message = "nome do setor não pode estar vazio")
    private Long sectorId;
}
