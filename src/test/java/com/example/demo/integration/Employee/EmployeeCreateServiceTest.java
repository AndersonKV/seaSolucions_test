package com.example.demo.integration.Employee;

import com.example.demo.DTO.EmployeeDTO.EmployeeDTO;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Employment;
import com.example.demo.entities.Sector;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import com.example.demo.service.EmployeeService.EmployeeCreateService;
import com.example.demo.service.EmploymentService.EmploymentCreateService;
import com.example.demo.utils.EmployeeFactory;
import com.example.demo.utils.EmploymentFactory;
import com.example.demo.utils.SectorFactory;
 import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@DisplayName("TEST INTEGRATION - EmployeeCreateServiceTest")
public class EmployeeCreateServiceTest {

    @Autowired
    EmployeeCreateService employeeCreateService;

    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmploymentRepository employmentRepository;

    @AfterEach
    void tearDown() {
        this.employmentRepository.deleteAll();
        this.employeeRepository.deleteAll();
        this.sectorRepository.deleteAll();
    }

    @Test
    @DisplayName("should create employee")
    public void shouldCreateEmployee() {
        Sector sector = new SectorFactory().create("setor 200");

        Sector sectorCreated = this.sectorRepository.save(sector);

        Employment employmentFactory = new EmploymentFactory().create(sectorCreated.getId(), "cargo 9000");

        Employment employmentCreated = this.employmentRepository.save(employmentFactory);

        EmployeeDTO employee = new EmployeeFactory().create("andy", "12345678911", employmentCreated.getId(), sectorCreated.getId());

       ResponseEntity res = this.employeeCreateService.create(employee);

        Assertions.assertEquals(HttpStatus.CREATED, res.getStatusCode());
     }


    @Test
    @DisplayName("should fail in create employee wrong employement id")
    public void shouldFailInCreateEmployeeWrongEmploymentId() {
        try {
            Sector sector = new SectorFactory().create("setor 200333");

            Sector sectorCreated = this.sectorRepository.save(sector);

            Employment employmentFactory = new EmploymentFactory().create(sectorCreated.getId(), "cargo 9000");

            EmployeeDTO employee = new EmployeeFactory().create("andy", "0000000000", 555l, sectorCreated.getId());
            this.employeeCreateService.create(employee);

        } catch (RuntimeException e) {
            Assertions.assertEquals("nenhum cargo com esse id 555 foi encontrado", e.getMessage());
        }
    }

    @Test
    @DisplayName("should fail in create employee wrong sector id")
    public void shouldFailInCreateEmployeeWrongSectorId() {
        try {
            Sector sector = new SectorFactory().create("setor 20033344");
            Sector sectorCreated = this.sectorRepository.save(sector);


            Employment employmentFactory = new EmploymentFactory().create(sectorCreated.getId(), "cargo 9044400");
            Employment employmentCreated = this.employmentRepository.save(employmentFactory);


            EmployeeDTO employee = new EmployeeFactory().create("andy", "0000000000", employmentCreated.getId(), 4444l);

            this.employeeCreateService.create(employee);

        } catch (RuntimeException e) {
            Assertions.assertEquals("nenhum setor com esse id 4444 foi encontrado", e.getMessage());
        }
    }

    @Test
    @DisplayName("should fail in create employee wrong digits cpf")
    public void shouldFailInCreateWrongCPFDigits() {
        try {
            Sector sector = new SectorFactory().create("setor 2003334444");
            Sector sectorCreated = this.sectorRepository.save(sector);

            Employment employmentFactory = new EmploymentFactory().create(sectorCreated.getId(), "cargo 900440");
            Employment employmentCreated = this.employmentRepository.save(employmentFactory);

            EmployeeDTO employee = new EmployeeFactory().create("andy", "8198189w", employmentCreated.getId(), sectorCreated.getId());

            this.employeeCreateService.create(employee);
        } catch (RuntimeException e) {
            Assertions.assertEquals("CPF deve ter 11 digitos", e.getMessage());
        }
    }


    @Test
    @DisplayName("should fail cpf has register")
    public void shouldFailInCPFHasRegister() {
        try {
            Sector sector = new SectorFactory().create("setor 200335534444");
            Sector sectorCreated = this.sectorRepository.save(sector);

            Employment employmentFactory = new EmploymentFactory().create(sectorCreated.getId(), "cargo 900440");
            Employment employmentCreated = this.employmentRepository.save(employmentFactory);

            EmployeeDTO employee = new EmployeeFactory().create("andy", "0000000", employmentCreated.getId(), sectorCreated.getId());


           this.employeeCreateService.create(employee);

        } catch (RuntimeException e) {
            Assertions.assertEquals("CPF deve ter 11 digitos", e.getMessage());
        }
    }


}
