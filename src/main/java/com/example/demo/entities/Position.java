package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Getter
@Setter
@Entity
@Table(name = "tb_position")
public class Position {
    @Id
    @SequenceGenerator(name = "positions_sequence", sequenceName = "positions_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "positions_sequence")
    private Long id;
    private String name;
    private String sectorName;

    private Long sectorId;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Position position;

}
