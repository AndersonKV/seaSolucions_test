package com.example.demo.e2e.Employee;

import com.example.demo.DTO.EmployeeDTO.EmployeeDTO;
import com.example.demo.DTO.EmployeeDTO.EmployeeUpdateDTO;
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
@DisplayName("E2E - EmployeeUpdateController")
public class EmployeeUpdateControllerTest {

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
    @DisplayName("should update employee")
    void shouldUpdateEmployee() throws Exception {

        Sector sector = new SectorFactory().create("setoxr 0xxx0");

        Sector created = this.sectorRepository.save(sector);

        Employment employmentFactory = new EmploymentFactory().create(created.getId(), created.getSectorName());

        Employment employment = new EmploymentFactory().create(created.getId(), "cargxxo 0005x");

        Employment createdEmployee = this.employmentRepository.save(employment);

        EmployeeDTO employee = new EmployeeFactory().create("andy", "12300678911", createdEmployee.getId(), created.getId());

        ResponseEntity<Employee> createdEmplowyee = this.employeeCreateService.create(employee);

        EmployeeUpdateDTO updateEmployee = new EmployeeFactory().update("novo nome atualizado", createdEmplowyee.getBody().getCpf(), createdEmplowyee.getBody().getEmploymentId(), createdEmplowyee.getBody().getSectorId());

        var res = mockMvc.perform(MockMvcRequestBuilders
                .put("/api/v1/employee/update/"+createdEmplowyee.getBody().getId())
                .content(asJsonString(updateEmployee))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());



    }

    @Test
    @DisplayName("should fail in update employee by cpf already exist")
    void shouldFailInUpdateEmployeeCPFAlreadyExist() throws Exception {

        String CPFAlreadyExist = "44444444444";

        Sector sector = new SectorFactory().create("setoxxr 0xxx0");

        Sector created = this.sectorRepository.save(sector);

        Employment employmentFactory = new EmploymentFactory().create(created.getId(), created.getSectorName());

        Employment employment = new EmploymentFactory().create(created.getId(), "cargxxxxo 0005x");

        Employment createdEmployee = this.employmentRepository.save(employment);

        EmployeeDTO employee = new EmployeeFactory().create("andy", CPFAlreadyExist, createdEmployee.getId(), created.getId());
        EmployeeDTO employee2 = new EmployeeFactory().create("andy", "12340278911", createdEmployee.getId(), created.getId());

        ResponseEntity<Employee> createdEmplowyee1 = this.employeeCreateService.create(employee);
        ResponseEntity<Employee> createdEmplowyee2 = this.employeeCreateService.create(employee2);

        EmployeeUpdateDTO updateEmployee = new EmployeeFactory().update("novo nome atualizado", CPFAlreadyExist, createdEmplowyee2.getBody().getEmploymentId(), createdEmplowyee2.getBody().getSectorId());

        mockMvc.perform(MockMvcRequestBuilders
                .put("/api/v1/employee/update/"+createdEmplowyee2.getBody().getId())
                .content(asJsonString(updateEmployee))
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
