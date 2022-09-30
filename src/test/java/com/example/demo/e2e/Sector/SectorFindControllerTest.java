package com.example.demo.e2e.Sector;

import com.example.demo.entities.Sector;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import com.example.demo.utils.SectorFactory;
import org.junit.jupiter.api.AfterEach;
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
@DisplayName("E2E - SectorFindController")
public class SectorFindControllerTest {

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
    @DisplayName("should find all sector")
    void shouldFindAllSectors() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/sector")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

    }

    @Test
    @DisplayName("should find by id sector")
    void shouldFindSectorById() throws Exception {
        Sector sector = this.sectorRepository.save(new SectorFactory().create("setor 000xxs01"));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/sector/id/" + sector.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

    }


    @Test
    @DisplayName("should fail in find by id sector")
    void shouldFailInFindSectorById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/sector/id/" + 999)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }


}
