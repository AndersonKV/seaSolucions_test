package com.example.demo.e2e.Employee;

 import com.example.demo.controller.SectorController.SectorCreateController;
 import com.example.demo.entities.Employee;
import com.example.demo.entities.Employment;
import com.example.demo.entities.Sector;
import com.example.demo.repository.EmployeeRepository;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("E2E - EmployeeFindController")
public class EmployeeFindControllerTest {
    @Autowired
    SectorCreateController sectorCreateController;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SectorRepository sectorRepository;


    @Autowired
    EmploymentRepository employmentRepository;


    @Autowired
    EmployeeRepository employeeRepository;


    @Autowired
    EmployeeCreateService employeeCreateService;


    @AfterEach
    void tearDown() {
        this.sectorRepository.deleteAll();
        this.employeeRepository.deleteAll();
        this.employmentRepository.deleteAll();
    }

    @Test
    @DisplayName("should find employee by id")
    void shouldFindEmployeeById() throws Exception {

        Sector sector = new SectorFactory().create("setor 0wwxxx0");

        Sector sectorCreated = this.sectorRepository.save(sector);

        Employment employment = new EmploymentFactory().create(sectorCreated.getId(), "cargo 0www005x");

        Employment createdEmployment = this.employmentRepository.save(employment);

        Employee employee = new EmployeeFactory().createNotDTO("andy", "12345678911", createdEmployment.getId(), sectorCreated.getId());

        Employee createdEmployee = this.employeeRepository.save(employee);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/employee/id/" + createdEmployee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    @DisplayName("should fail in find by name")
    void shouldFailInFindEmployeeByName() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/employee/find_by_name")
                .param("name", "55s5s5s")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("should fail in find employee by id")
    void shouldFailInFindEmployeeById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/employee/id/" + 466l)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("should find by cpf")
    void shouldFindByByCPF() throws Exception {

        Sector sector = new SectorFactory().create("set55or 0xxx0");

        Sector sectorFactory = this.sectorRepository.save(sector);

        Employment employmentFactory = new EmploymentFactory().create(sectorFactory.getId(), sectorFactory.getSectorName());

        Employment employment = new EmploymentFactory().create(sectorFactory.getId(), "cargo55");

        Employment createdEmployee = this.employmentRepository.save(employment);

        var employee = new EmployeeFactory().create("andy", "12345678988", createdEmployee.getId(), sectorFactory.getId());

         ResponseEntity<Employee> result = this.employeeCreateService.create(employee);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/employee/find_by_cpf")
                .param("cpf", result.getBody().getCpf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }


    @Test
    @DisplayName("should fail in find by cpf")
    void shouldFailInFindByByCPF() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/employee/find_by_cpf")
                .param("cpf", asJsonString("6515616565"))
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
