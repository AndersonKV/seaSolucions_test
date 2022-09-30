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
public class EmploymentCreateControllerTest {

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
    @DisplayName("should create employment")
    void shouldCreateEmployment() throws Exception {

        Sector sector = new SectorFactory().create("setor 2201");
        Sector created = this.sectorRepository.save(sector);

        Employment employmentFactory = new EmploymentFactory().create(created.getId(), created.getSectorName());

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/employment/create")
                .content(asJsonString(employmentFactory))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }


    @Test
    @DisplayName("should fail in create employment already exists")
    void shouldFailInCreateEmploymentAlreadyExists() throws Exception {

        Sector sectorShouldThrown = new SectorFactory().create("setor 2x201");

        Sector createdSector1  =this.sectorRepository.save(sectorShouldThrown);

        Employment employmentFactory1
                = new EmploymentFactory().create(createdSector1.getId(),"cargo x000x");

        this.employmentRepository.save(employmentFactory1);

        Sector sector2 = new SectorFactory().create("setor 2x202");
        Sector createdSector2 = this.sectorRepository.save(sector2);

        Employment employmentFactory2 = new EmploymentFactory().create(createdSector2.getId(), employmentFactory1.getName());

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/employment/create")
                .content(asJsonString(employmentFactory2))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }


    @Test
    @DisplayName("should fail create employment with sector id wrong")
    void shouldFailInCreateEmploymentByWrongId() throws Exception {

        Employment employmentFactory = new EmploymentFactory().create(1111l, "xxxxx");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/employment/create")
                .content(asJsonString(employmentFactory))
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
