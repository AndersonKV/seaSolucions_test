package com.example.demo.repository;

import com.example.demo.entities.Employee;
import com.example.demo.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
}