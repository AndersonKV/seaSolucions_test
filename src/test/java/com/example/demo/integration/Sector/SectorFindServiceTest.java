package com.example.demo.integration.Sector;


import com.example.demo.entities.Sector;
import com.example.demo.repository.SectorRepository;
import com.example.demo.service.SectorService.SectorCreateService;
import com.example.demo.service.SectorService.SectorFindService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@DisplayName("TEST INTEGRATION - SectorFindServiceTest")
public class SectorFindServiceTest {
    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    SectorCreateService sectorCreateService;

    @Autowired
    SectorFindService sectorFindService;


    @Test
    @DisplayName("should find all sectors")
    public void shouldFindAll() {
        ResponseEntity res = this.sectorFindService.findAll();
        Assertions.assertEquals(HttpStatus.ACCEPTED, res.getStatusCode());
    }


}
