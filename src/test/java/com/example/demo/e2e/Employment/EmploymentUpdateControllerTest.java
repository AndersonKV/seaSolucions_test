package com.example.demo.e2e.Employment;

import com.example.demo.DTO.EmploymentDTO.EmploymentUpdateDTO;
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
@DisplayName("E2E - EmploymentUpdateController")
public class EmploymentUpdateControllerTest {
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
    @DisplayName("should update employment")
    void shouldUpdateEmployment() throws Exception {

        Sector sector = new SectorFactory().create("setor 2xs3201");

        Sector createdSector = this.sectorRepository.save(sector);

        Employment employmentFactory = new EmploymentFactory().create(createdSector.getId(), "cargo x9x99");

        Employment createdEmployment = this.employmentRepository.save(employmentFactory);

        EmploymentUpdateDTO updateEmployment = new EmploymentFactory().update(createdSector.getId(), "novo cargo 000");

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/v1/employment/update/" + createdEmployment.getId())
                .content(asJsonString(updateEmployment))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }


    @Test
    @DisplayName("should fail in update employment already name")
    void shouldFailInUpdateEmploymentAlreadyName() throws Exception {

        Sector sector = new SectorFactory().create("setor 2xsxss3201");
        Sector createdSector = this.sectorRepository.save(sector);

        Employment employmentFactory1 = new EmploymentFactory().create(createdSector.getId(), "cargo x9xsss99");
        Employment employmentFactory2 = new EmploymentFactory().create(createdSector.getId(), "cargo www");

        this.employmentRepository.save(employmentFactory1);
        Employment createdEmployment = this.employmentRepository.save(employmentFactory2);

        EmploymentUpdateDTO updateEmployment = new EmploymentFactory().update(createdSector.getId(), employmentFactory1.getName());

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/v1/employment/update/" + createdEmployment.getId())
                .content(asJsonString(updateEmployment))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("should fail in update employment by sector id not exist")
    void shouldFailInUpdateEmploymentBySectorIdNotExist() throws Exception {

        Sector sector = new SectorFactory().create("setor 3sxs3a20www1");

        Sector createdSector = this.sectorRepository.save(sector);

        Employment employmentFactory = new EmploymentFactory().create(createdSector.getId(), "cargo x9ssjx99");

        Employment createdEmployment = this.employmentRepository.save(employmentFactory);

        EmploymentUpdateDTO updateEmployment = new EmploymentFactory().update(5555l, "novo cargo j000");

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/v1/employment/update/" + createdEmployment.getId())
                .content(asJsonString(updateEmployment))
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
