package com.example.demo.repository;


import com.example.demo.entities.Employee;
import com.example.demo.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByCPF(String cpf);

    List<Employee> findByName(String name);

//    Optional<Employee> findByPositionNameAndSectorName(String position, String sector);
//
    List<Employee> findByPositionIdAndSectorId(Long p, Long s);

    List<Employee> findByPositionId(Long p);

}