package com.example.demo.integration.Employee;

import com.example.demo.DTO.EmployeeDTO.EmployeeDTO;
import com.example.demo.DTO.EmployeeDTO.EmployeeUpdateDTO;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Employment;
import com.example.demo.entities.Sector;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import com.example.demo.service.EmployeeService.EmployeeCreateService;
import com.example.demo.service.EmployeeService.EmployeeFindService;
import com.example.demo.service.EmployeeService.EmployeeUpdateService;
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

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
@DisplayName("TEST INTEGRATION - EmployeeUpdateServiceTest")
public class EmployeeUpdateServiceTest {
    @Autowired
    EmployeeCreateService employeeCreateService;

    @Autowired
    EmployeeFindService employeeFindService;

    @Autowired
    EmployeeUpdateService employeeUpdateService;

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
    @DisplayName("should fail in update employee")
    public void shouldUpdateEmployee() {
        Sector sector = new SectorFactory().create("setor 2w00");

        Sector sectorCreated = this.sectorRepository.save(sector);

        Employment employmentFactory = new EmploymentFactory().create(sectorCreated.getId(), "cargo 90w00");

        Employment employmentCreated = this.employmentRepository.save(employmentFactory);

        EmployeeDTO employee = new EmployeeFactory().create("andy", "10345678911", employmentCreated.getId(), sectorCreated.getId());

        this.employeeCreateService.create(employee);

        ResponseEntity<Employee> getEmployeeByCPF = this.employeeFindService.findByCpf(employee.getCPF());

        EmployeeUpdateDTO employeeUpdate = new EmployeeFactory().update("novo andy", "10345678911", employmentCreated.getId(), sectorCreated.getId());

        ResponseEntity res = this.employeeUpdateService.update(getEmployeeByCPF.getBody().getId(), employeeUpdate);

        Assertions.assertEquals(HttpStatus.CREATED, res.getStatusCode());

    }

    @Test
    @DisplayName("should fail in update employee cpf has exist")
    public void shouldFailUpdateEmployeeCPFHasExist() {
        try {
            Sector sector = new SectorFactory().create("setor 2wzz00");

            Sector sectorCreated = this.sectorRepository.save(sector);

            Employment employmentFactory = new EmploymentFactory().create(sectorCreated.getId(), "cargo 90ws00");

            Employment employmentCreated = this.employmentRepository.save(employmentFactory);

            EmployeeDTO employee = new EmployeeFactory().create("andy", "00000000000", employmentCreated.getId(), sectorCreated.getId());
            EmployeeDTO employee2 = new EmployeeFactory().create("andy", "11111111111", employmentCreated.getId(), sectorCreated.getId());

            this.employeeCreateService.create(employee);
            this.employeeCreateService.create(employee2);

            ResponseEntity<Employee> getEmployeeByCPF = this.employeeFindService.findByCpf(employee.getCPF());

            EmployeeUpdateDTO employeeUpdate = new EmployeeFactory().update("novo andy", employee2.getCPF(), employmentCreated.getId(), sectorCreated.getId());

            this.employeeUpdateService.update(getEmployeeByCPF.getBody().getId(), employeeUpdate);

        } catch (RuntimeException e) {
            Assertions.assertEquals("esse cpf 11111111111 ja esta registrado", e.getMessage());
        }
    }


    @Test
    @DisplayName("should fail in update employee wrong id")
    public void shouldFailInUpdateEmployeeWithWrongID() {
        try {

            EmployeeUpdateDTO employeeUpdate = new EmployeeFactory().update("novo andy", "10345078911", 1000l, 50l);

            this.employeeUpdateService.update(555l, employeeUpdate);
        } catch (RuntimeException e) {
            Assertions.assertEquals("esse id 555 não foi encontrado", e.getMessage());
        }
    }

    @Test
    @DisplayName("should fail in update employee wrong employee id")
    public void shouldFailInUpdateEmployeeWithWrongEmployeeId() {
        try {
            Sector sector = new SectorFactory().create("setor 2wzzz00");

            Sector sectorCreated = this.sectorRepository.save(sector);

            Employment employmentFactory = new EmploymentFactory().create(sectorCreated.getId(), "cargo 90w0zzz0");

            Employment employmentCreated = this.employmentRepository.save(employmentFactory);

            EmployeeDTO employee = new EmployeeFactory().create("andy", "10344678911", employmentCreated.getId(), sectorCreated.getId());

            this.employeeCreateService.create(employee);

            ResponseEntity<Employee> getEmployeeByCPF = this.employeeFindService.findByCpf(employee.getCPF());

            EmployeeUpdateDTO employeeUpdate = new EmployeeFactory().update("novo andy", "10345078911", 1000l, sectorCreated.getId());

            this.employeeUpdateService.update(getEmployeeByCPF.getBody().getId(), employeeUpdate);
        } catch (RuntimeException e) {
            Assertions.assertEquals("nenhum cargo com esse id 1000 foi encontrado", e.getMessage());
        }
    }


}
