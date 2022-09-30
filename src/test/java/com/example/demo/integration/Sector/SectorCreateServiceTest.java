package com.example.demo.integration.Sector;

import com.example.demo.entities.Sector;
import com.example.demo.repository.SectorRepository;
import com.example.demo.service.SectorService.SectorCreateService;
import com.example.demo.service.SectorService.SectorUpdateService;
import com.example.demo.utils.SectorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;


@SpringBootTest
@RunWith(SpringRunner.class)
@DisplayName("TEST INTEGRATION - SectorCreateServiceTest")
public class SectorCreateServiceTest {
    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    SectorCreateService sectorCreateService;


    @AfterEach
    void tearDown() {
        this.sectorRepository.deleteAll();
    }

    @Test
    @DisplayName("should create sector")
    public void shouldCreateSector() {
        Sector sector = new SectorFactory().create("setor 1");

        ResponseEntity res = this.sectorCreateService.create(sector);

        Assertions.assertEquals(HttpStatus.CREATED, res.getStatusCode());
    }

    @Test
    @DisplayName("should fail in create sector")
    public void shouldFailInCreateSector() {
        try {
            Sector sector = new SectorFactory().create("setor 1");

            this.sectorCreateService.create(sector);
            this.sectorCreateService.create(sector);

        } catch (RuntimeException e) {
            Assertions.assertEquals("já existe um setor com esse nome: setor 1", e.getMessage());
        }

    }

}
