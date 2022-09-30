package com.example.demo.repository;


import com.example.demo.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByCpf(String cpf);

    List<Employee> findByNameEmployee(String name);

    List<Employee> findByEmploymentIdAndSectorId(Long e, Long s);

}