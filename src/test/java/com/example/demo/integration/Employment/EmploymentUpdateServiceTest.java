package com.example.demo.integration.Employment;

import com.example.demo.DTO.EmploymentDTO.EmploymentUpdateDTO;
import com.example.demo.entities.Employment;
import com.example.demo.entities.Sector;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import com.example.demo.service.EmploymentService.EmploymentCreateService;
import com.example.demo.service.EmploymentService.EmploymentFindService;
import com.example.demo.service.EmploymentService.EmploymentUpdateService;
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
@DisplayName("TEST INTEGRATION - EmploymentUpdateServiceTest")
public class EmploymentUpdateServiceTest {
    @Autowired
    EmploymentRepository employmentRepository;

    @Autowired
    EmploymentCreateService employmentCreateService;

    @Autowired
    EmploymentUpdateService employmentUpdateService;

    @Autowired
    EmploymentFindService employmentFindService;

    @Autowired
    SectorRepository sectorRepository;


    @AfterEach
    void tearDown() {
        this.sectorRepository.deleteAll();
        this.employmentRepository.deleteAll();

    }


    @Test
    @DisplayName("should update employment name")
    public void shouldUpdateEmploymentName() {

        Sector sector = new SectorFactory().create("setor dzzzsww");

        Sector sectorCreated = this.sectorRepository.save(sector);

        Employment employment = new EmploymentFactory().create(sectorCreated.getId(), "cargo 6666xxx66");

        Employment createdEmployment = this.employmentRepository.save(employment);

        EmploymentUpdateDTO updateEmployment = new EmploymentFactory().update(sectorCreated.getId(), "update cargo wwxaww");

        ResponseEntity res = this.employmentUpdateService.update(createdEmployment.getId(), updateEmployment);

        Assertions.assertEquals(HttpStatus.ACCEPTED, res.getStatusCode());

    }

    @Test
    @DisplayName("should fail update employment name by id")
    public void shouldFailInUpdateEmploymentNameById() {
        try {
            EmploymentUpdateDTO updateEmployment = new EmploymentFactory().update(999l, "update cargo wwww");

            ResponseEntity res = this.employmentUpdateService.update(updateEmployment.getSectorId(), updateEmployment);

            Assertions.assertEquals(HttpStatus.ACCEPTED, res.getStatusCode());
        } catch (RuntimeException e) {
            Assertions.assertEquals("nenhum cargo com esse id 999 foi encontrado", e.getMessage());
        }
    }

    @Test
    @DisplayName("should fail update employment name has exist")
    public void shouldFailInUpdateEmploymentNameHasExist() {
        try {
            Sector sector = new SectorFactory().create("setor 4444");

            Sector createdSector = this.sectorRepository.save(sector);

            Employment firstEmployment = new EmploymentFactory().create(createdSector.getId(), "cargo 5050");

            Employment secondEmployment = new EmploymentFactory().create(createdSector.getId(), "cargo 1010");

            this.employmentCreateService.create(firstEmployment);

            Employment createdEmployment = this.employmentRepository.save(secondEmployment);

            EmploymentUpdateDTO updateEmployment = new EmploymentFactory().update(createdSector.getId(), firstEmployment.getName());

            this.employmentUpdateService.update(createdEmployment.getId(), updateEmployment);
        } catch (RuntimeException e) {
            Assertions.assertEquals("esse nome cargo 5050 já foi registrado", e.getMessage());
        }
    }


}
