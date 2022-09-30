package com.example.demo.e2e.Sector;

import com.example.demo.controllers.EmployeeController.SectorCreateController;
import com.example.demo.entities.Sector;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import com.example.demo.utils.SectorFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("E2E - SectorUpdateController")
public class SectorUpdateControllerTest {
    @Autowired
    SectorCreateController sectorCreateController;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmploymentRepository employmentRepository;

    @AfterEach
    void tearDown() {
        this.sectorRepository.deleteAll();
    }

    @Test
    @DisplayName("should update sector")
    void shouldUpdateSector() throws Exception {

        Sector sector = new SectorFactory().create("setor x11");

        Sector createdSector = this.sectorRepository.save(sector);

        Sector sectorUpdate = new SectorFactory().update(createdSector.getId(), "novo nome");
        ;

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/v1/sector/update/" + createdSector.getId())
                .content(asJsonString(sectorUpdate))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    @DisplayName("should fail in update sector name has exist")
    void shouldFailInUpdateSectorNameHasExist() throws Exception {

        Sector sector1 = new SectorFactory().create("setor 1xxxxxxx11");
        Sector sector2 = new SectorFactory().create("setor 2x1xxssss1");

        Sector createdSector = this.sectorRepository.save(sector1);

        this.sectorRepository.save(sector2);

        Sector sectorUpdate = new SectorFactory().update(createdSector.getId(), sector2.getSectorName());


        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/v1/sector/update/" + createdSector.getId())
                .content(asJsonString(sectorUpdate))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("should fail in update sector with wrong id")
    void shouldFailInUpdateSectorWithWrongId() throws Exception {

        Sector sectorUpdate = new SectorFactory().update(666l, "novo nome");
        ;

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/v1/sector/update/" + sectorUpdate.getId())
                .content(asJsonString(sectorUpdate))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
