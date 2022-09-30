package com.example.demo.integration.Employment;

import com.example.demo.entities.Employment;
import com.example.demo.entities.Sector;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import com.example.demo.service.EmploymentService.EmploymentCreateService;
import com.example.demo.service.SectorService.SectorCreateService;
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
@DisplayName("TEST INTEGRATION - EmploymentCreateServiceTest")
public class EmploymentCreateServiceTest {
    @Autowired
    EmploymentRepository employmentRepository;

    @Autowired
    EmploymentCreateService employmentCreateService;

    @Autowired
    SectorRepository sectorRepository;


    @AfterEach
    void tearDown() {
        this.employmentRepository.deleteAll();
    }

    @Test
    @DisplayName("should create employment")
    public void shouldCreateEmployment() {

        Sector sector = new SectorFactory().create("setor do employment 1");

        Sector createdSector = this.sectorRepository.save(sector);

        Employment employmentFactory = new EmploymentFactory().create(createdSector.getId(), "cargo 1");

        ResponseEntity res = this.employmentCreateService.create(employmentFactory);

        Assertions.assertEquals(HttpStatus.CREATED, res.getStatusCode());
    }

    @Test
    @DisplayName("should fail in create employment sector id wrong")
    public void shouldFailInCreateEmploymentWithSectorIdWrong() {
        try {
            Employment employmentFactory = new EmploymentFactory().create(999l, "cargo 555");

            this.employmentCreateService.create(employmentFactory);
        } catch (RuntimeException e) {
            Assertions.assertEquals("esse id 999 do setor não foi encontrado", e.getMessage());
        }
    }


    @Test
    @DisplayName("should fail in create employment name has exist")
    public void shouldFailInCreateEmploymentWithNameHasExist() {
        try {
            Sector sector = new SectorFactory().create("setor do employment 1444");

            Sector createdSector = this.sectorRepository.save(sector);

            Employment employmentFactory = new EmploymentFactory().create(createdSector.getId(), "cargo 5");
            Employment employmentFactory2 = new EmploymentFactory().create(createdSector.getId(), "cargo 5");

            this.employmentCreateService.create(employmentFactory);
            this.employmentCreateService.create(employmentFactory2);
        } catch (RuntimeException e) {
            Assertions.assertEquals("esse cargo já foi registrado", e.getMessage());
        }
    }


}
