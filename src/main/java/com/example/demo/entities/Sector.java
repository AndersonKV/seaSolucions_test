package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    private String name;
//
//    @OneToMany(mappedBy="id")
//    private List<Position> position;
//
//    @OneToMany(mappedBy="id")
//    private List<Employee> employee;



}


