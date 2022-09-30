package com.example.demo.integration.Employee;

import com.example.demo.DTO.EmployeeDTO.EmployeeDTO;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Employment;
import com.example.demo.entities.Sector;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import com.example.demo.service.EmployeeService.EmployeeCreateService;
import com.example.demo.service.EmployeeService.EmployeeFindService;
import com.example.demo.utils.EmployeeFactory;
import com.example.demo.utils.EmploymentFactory;
import com.example.demo.utils.SectorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@DisplayName("TEST INTEGRATION - EmployeeFindServiceTest")
public class EmployeeFindServiceTest {
    @Autowired
    EmployeeCreateService employeeCreateService;

    @Autowired
    EmployeeFindService employeeFindService;


    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmploymentRepository employmentRepository;

    @AfterEach
    void tearDown() {
        this.employmentRepository.deleteAll();
        this.employeeRepository.deleteAll();
        this.sectorRepository.deleteAll();
    }


    @Test
    @DisplayName("should find all employees")
    public void shouldFindAllEmployee() {
        ResponseEntity<List<Employee>> res = this.employeeFindService.findAll();
        Assertions.assertEquals(HttpStatus.ACCEPTED, res.getStatusCode());
    }

    @Test
    @DisplayName("should find by CPF")
    public void shouldFindByCPF() {
        Sector sector = new SectorFactory().create("setor do employment 133");

        Sector createdSector = this.sectorRepository.save(sector);

        Employment employmentFactory = new EmploymentFactory().create(createdSector.getId(), "cargo 1");

        Employment createdEmployment = this.employmentRepository.save(employmentFactory);

        EmployeeDTO employee = new EmployeeFactory().create("andy", "12345678911", createdEmployment.getId(), createdEmployment.getSectorId());

        this.employeeCreateService.create(employee);

        ResponseEntity res = this.employeeFindService.findByCpf(employee.getCPF());
        Assertions.assertEquals(HttpStatus.ACCEPTED, res.getStatusCode());
    }

    @Test
    @DisplayName("should fail in find by CPF")
    public void shouldFailInFindByCPF() {
        try {

            this.employeeFindService.findByCpf("651561566");
        } catch (RuntimeException e) {
            Assertions.assertEquals("nenhum cpf com esses numero foi encontrado: 651561566", e.getMessage());
        }
    }


    @Test
    @DisplayName("should find by employee name")
    public void shouldFindByEmployeeName() {
        Sector sector = new SectorFactory().create("setor do employment 14433");

        Sector createdSector = this.sectorRepository.save(sector);

        Employment employmentFactory = new EmploymentFactory().create(createdSector.getId(), "cargo 5555");

        Employment createdEmployment = this.employmentRepository.save(employmentFactory);

        EmployeeDTO employee = new EmployeeFactory().create("andy", "12345678915", createdEmployment.getId(), createdEmployment.getSectorId());

        this.employeeCreateService.create(employee);

        ResponseEntity res = this.employeeFindService.findByName(employee.getName());
        Assertions.assertEquals(HttpStatus.ACCEPTED, res.getStatusCode());
    }


    @Test
    @DisplayName("should fail in find by employee name")
    public void shouldFailInFindByEmployeeName() {
        try {
            ResponseEntity res = this.employeeFindService.findByName("wwwpp ww");
            Assertions.assertEquals(HttpStatus.ACCEPTED, res.getStatusCode());
        } catch (RuntimeException e) {
            Assertions.assertEquals("nenhum empregado com esse nome foi encontrado: wwwpp ww", e.getMessage());
        }
    }


}
