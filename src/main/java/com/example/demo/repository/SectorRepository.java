package com.example.demo.repository;

import com.example.demo.entities.Employee;
import com.example.demo.entities.Position;
import com.example.demo.entities.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {
    Optional<Sector> findBySectorName(String name);

}