package com.example.demo.e2e.Sector;

import com.example.demo.controllers.EmployeeController.SectorCreateController;
import com.example.demo.entities.Sector;
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
@DisplayName("E2E - SectorCreateController")
public class SectorCreateControllerTest {

    @Autowired
    SectorCreateController sectorCreateController;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SectorRepository sectorRepository;

    @AfterEach
    void tearDown() {
        this.sectorRepository.deleteAll();
    }


    @Test
    @DisplayName("should create sector")
    void shouldCreateSector() throws Exception {

        Sector sector = new SectorFactory().create("setor 01");


        var result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/sector/create")
                .content(asJsonString(sector))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }


    @Test
    @DisplayName("should fail in create sector has exist")
    void shouldFailInCreateSectorHasExist() throws Exception {

        Sector sector = new SectorFactory().create("setor 001");

        this.sectorRepository.save(sector);


        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/sector/create")
                .content(asJsonString(sector))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("should fail in create sector empty values")
    void shouldFailInCreateSectorEmptyValues() throws Exception {

        Sector sector = new Sector();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/sector/create")
                .content(asJsonString(sector))
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
