package com.example.demo.e2e.Employee;

import com.example.demo.DTO.EmployeeDTO.EmployeeDTO;
import com.example.demo.controllers.EmployeeController.SectorCreateController;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Employment;
import com.example.demo.entities.Sector;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import com.example.demo.service.EmployeeService.EmployeeCreateService;
import com.example.demo.utils.EmployeeFactory;
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
@DisplayName("E2E - EmployeeCreateController")
public class EmployeeCreateControllerTest {
    @Autowired
    SectorCreateController sectorCreateController;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SectorRepository sectorRepository;


    @Autowired
    EmploymentRepository employmentRepository;


    @Autowired
    EmployeeCreateService employeeCreateService;



    @AfterEach
    void tearDown() {
        this.sectorRepository.deleteAll();
        this.employmentRepository.deleteAll();
        this.employmentRepository.deleteAll();
    }


    @Test
    @DisplayName("should create employee")
    void shouldCreateEmployment() throws Exception {

        Sector sector = new SectorFactory().create("setor 0xxx0");

        Sector created = this.sectorRepository.save(sector);

        Employment employmentFactory = new EmploymentFactory().create(created.getId(), created.getSectorName());

        Employment employment = new EmploymentFactory().create(created.getId(), "cargo 0005x");

        Employment createdEmployee = this.employmentRepository.save(employment);

        EmployeeDTO employee = new EmployeeFactory().create("andy", "12345678911", createdEmployee.getId(), created.getId());

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/employee/create")
                .content(asJsonString(employee))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    @DisplayName("should fail in create employee already cpf exist")
    void shouldFailInCreateEmployeeAlreadyExist() throws Exception {

        Sector sector = new SectorFactory().create("setor 0xxx0");

        Sector created = this.sectorRepository.save(sector);

        Employment employmentFactory = new EmploymentFactory().create(created.getId(), created.getSectorName());

        Employment employment = new EmploymentFactory().create(created.getId(), "cargo 0005x");

        Employment createdEmployee = this.employmentRepository.save(employment);

        EmployeeDTO employee = new EmployeeFactory().create("andy", "999999999", createdEmployee.getId(), created.getId());


        this.employeeCreateService.create(employee);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/employee/create")
                .content(asJsonString(employee))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("should fail in create employee by wrong sector id")
    void shouldFailInCreateEmploymentByWrongSectorId() throws Exception {

        Sector sector = new SectorFactory().create("setor 0xw4xx0");

        Sector created = this.sectorRepository.save(sector);

        Employment employment = new EmploymentFactory().create(created.getId(), "cargo 0005w3x");

        Employment createdEmployee = this.employmentRepository.save(employment);

        EmployeeDTO employee = new EmployeeFactory().create("andy", "12345278511", createdEmployee.getId(), 55555l);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/employee/create")
                .content(asJsonString(employee))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("should fail in create employee by wrong employement id")
    void shouldFailInCreateEmploymentByWrongEmploymentId() throws Exception {

        Sector sector = new SectorFactory().create("setor 0xssxx0");

        Sector sectorCreated = this.sectorRepository.save(sector);

        Employment employmentFactory = new EmploymentFactory().create(sectorCreated.getId(), sectorCreated.getSectorName());

        Employment employment = new EmploymentFactory().create(sectorCreated.getId(), "cargo 000x5x");

        Employment createdEmployee = this.employmentRepository.save(employment);

        EmployeeDTO employee = new EmployeeFactory().create("andy", "12345675911", 444l, sectorCreated.getId());

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/employee/create")
                .content(asJsonString(employee))
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

