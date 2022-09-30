package com.example.demo.integration.Employment;

import com.example.demo.entities.Employment;
import com.example.demo.entities.Sector;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import com.example.demo.service.EmploymentService.EmploymentCreateService;
import com.example.demo.service.EmploymentService.EmploymentFindService;
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

@SpringBootTest
@RunWith(SpringRunner.class)
@DisplayName("TEST INTEGRATION - EmploymentFindServiceTest")
public class EmploymentFindServiceTest {
    @Autowired
    EmploymentRepository employmentRepository;

    @Autowired
    EmploymentCreateService employmentCreateService;


    @Autowired
    EmploymentFindService employmentFindService;

    @Autowired
    SectorRepository sectorRepository;


    @AfterEach
    void tearDown() {
        this.employmentRepository.deleteAll();
    }

    @Test
    @DisplayName("should find employment")
    public void shouldFindEmployment() {

        ResponseEntity res = this.employmentFindService.findAll();
        Assertions.assertEquals(HttpStatus.ACCEPTED, res.getStatusCode());
    }

    @Test
    @DisplayName("should by employment name")
    public void shouldFindByEmploymentName() {

        Sector sector = new SectorFactory().create("setor do employment 122");

        Sector createdSector = this.sectorRepository.save(sector);

        Employment employment = new EmploymentFactory().create(createdSector.getId(), "cargo 222");

        this.employmentCreateService.create(employment);

        ResponseEntity res = this.employmentFindService.findEmploymentByName(employment.getName());

        Assertions.assertEquals(HttpStatus.ACCEPTED, res.getStatusCode());
    }



    @Test
    @DisplayName("should fail in find by employment name")
    public void shouldFailInFindEmploymentByName() {
        String name = "capcap";

        try {
            this.employmentFindService.findEmploymentByName(name);
        } catch (RuntimeException e) {
            Assertions.assertEquals("nenhum cargo com esse nome " + name + " foi encontrado", e.getMessage());
        }
    }


}

