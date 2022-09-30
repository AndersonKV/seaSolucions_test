package com.example.demo.integration.Sector;

import com.example.demo.entities.Sector;
import com.example.demo.repository.SectorRepository;
import com.example.demo.service.SectorService.SectorCreateService;
import com.example.demo.service.SectorService.SectorUpdateService;
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

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@DisplayName("TEST INTEGRATION - SectorUpdateServiceTest")
public class SectorUpdateServiceTest {
    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    SectorUpdateService sectorUpdateService;

    @Autowired
    SectorCreateService sectorCreateService;


    @AfterEach
    void tearDown() {
        this.sectorRepository.deleteAll();
    }


    @Test
    @DisplayName("should update sector")
    public void shouldUpdateSector() {
        Sector sector = new Sector();
        sector.setSectorName("setor 2");

        Sector updateSector = new Sector();


        ResponseEntity<Sector> createdSector = this.sectorCreateService.create(sector);

        updateSector.setSectorName("another sector 2");
        updateSector.setId(createdSector.getBody().getId());

        ResponseEntity res = this.sectorUpdateService.update(createdSector.getBody().getId(), updateSector);

        Optional<Sector> getUpdatedSector = this.sectorRepository.findById(sector.getId());

        Assertions.assertEquals(HttpStatus.CREATED, res.getStatusCode());
        Assertions.assertEquals(getUpdatedSector.get().getSectorName(), updateSector.getSectorName());
    }

    @Test
    @DisplayName("should fail in update sector with wrong id")
    public void shouldFailUpdateSectorWrongId() {
        try {
            this.sectorUpdateService.update(5555l, new Sector());
        } catch (RuntimeException e) {
            Assertions.assertEquals("id não encontrado", e.getMessage());
        }
    }

    @Test
    @DisplayName("should fail in update with sector has existe")
    public void shouldFailInUpdateWithSectorHasExist() {
        try {
            Sector sector = new SectorFactory().create("setor  7");

            this.sectorCreateService.create(new SectorFactory().create("setor5"));

            ResponseEntity<Sector> getSector = this.sectorCreateService.create(sector);

            Sector updateSector = new SectorFactory().update(getSector.getBody().getId(), "setor  5");

            this.sectorUpdateService.update(updateSector.getId(), updateSector);
        } catch (RuntimeException e) {
            Assertions.assertEquals("já existe um setor com esse nome setor5", e.getMessage());
        }
    }

}
