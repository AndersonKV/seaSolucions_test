package com.example.demo.repository;

import com.example.demo.entities.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, Long> {
    Optional<Employment> findByPositionName(String name);
    List<Employment> findBySectorId(Long id);

}