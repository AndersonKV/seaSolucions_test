package com.example.demo.e2e.Employment;

import com.example.demo.controller.SectorController.SectorCreateController;
import com.example.demo.entities.Employment;
import com.example.demo.entities.Sector;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import com.example.demo.utils.EmploymentFactory;
import com.example.demo.utils.SectorFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@DisplayName("E2E - EmploymentCreateController")
public class EmploymentFindControllerTest {
    @Autowired
    SectorCreateController sectorCreateController;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SectorRepository sectorRepository;


    @Autowired
    EmploymentRepository employmentRepository;

    @AfterEach
    void tearDown() {
        this.sectorRepository.deleteAll();
        this.employmentRepository.deleteAll();
    }

    @Test
    @DisplayName("should find all employment")
    void shouldFindEmployment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/employment/find_all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

    }

    @Test
    @DisplayName("should find by employment name")
    void shouldFindEmploymentByName() throws Exception {

        Sector sector = new SectorFactory().create("setor 22xx0gsxs1");

        Sector created = this.sectorRepository.save(sector);

        Employment employmentFactory = new EmploymentFactory().create(created.getId(), "cargo gxxs");

        this.employmentRepository.save(employmentFactory);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/employment/find_by_employment")
                .param("name", employmentFactory.getName())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());


    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
