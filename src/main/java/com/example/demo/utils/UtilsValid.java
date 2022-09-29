package com.example.demo.utils;

import com.example.demo.DTO.EmployeeDTO.EmployeeUpdateDTO;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Employment;
import com.example.demo.entities.Sector;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmploymentRepository;
import com.example.demo.repository.SectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class UtilsValid {
    private EmployeeRepository employeeRepository;
    private EmploymentRepository employmentRepository;
    private SectorRepository sectorRepository;

    protected Employee checkIsUserExist(Long id) {

        var findEmployeeById = this.employeeRepository.findById(id);

        if (findEmployeeById.isEmpty()) {
            throw new ApiRequestException("nenhum empregado com esse id " + id + " foi encontrado");
        }

        return findEmployeeById.get();
    }

    protected void employeeHasRegisterWithCPF(String cpf) {
        Optional<Employee> employeeIsRegister = this.employeeRepository.findByCPF(cpf);

        if (employeeIsRegister.isPresent()) {
            throw new ApiRequestException("esse cpf " + cpf + " já foi registrado");
        }
    }

    protected Employment employmentExist(Long id) {
        Optional<Employment> employment = this.employmentRepository.findById(id);

        if (employment.isEmpty()) {
            throw new ApiRequestException("nenhum cargo com esse id " + id + " foi encontradofoi encontrado");
        }

        return employment.get();
    }

    protected void employmentNameUpdate(String actualName, String updateName) {
        if(!actualName.equals(updateName)) {
            Optional<Employment> employmentNameExist = this.employmentRepository.findByName(updateName);

            if(employmentNameExist.isPresent()) {
                throw new ApiRequestException("esse nome " + updateName + " já foi registrado");
            }
        }
    }

    protected void employmentUpdateSectorExist(Long sectorId, Long updateSectorId) {
        if(!sectorId.equals(updateSectorId)) {
            Optional<Sector> sector = this.sectorRepository.findById(updateSectorId);

            if(sector.isEmpty()) {
                throw new ApiRequestException("esse id " + updateSectorId + " não foi encontrado");
            }
        }

    }


    protected Employment getEmploymentById(Long id) {
        Optional<Employment> employment = this.employmentRepository.findById(id);

        if (employment.isEmpty()) {
            throw new ApiRequestException("esse id " + id + " não foi encontrado");
        }

        return employment.get();
    }

    protected Sector sectorExist(Long id) {
        Optional<Sector> sectorExist = this.sectorRepository.findById(id);

        if (sectorExist.isEmpty()) {
            throw new ApiRequestException("nenhum setor com esse id " + id + " foi encontrado");
        }

        return sectorExist.get();

    }

    protected Sector getSectorById(Long id) {
        Optional<Sector> sectorExist = this.sectorRepository.findById(id);

        if (sectorExist.isEmpty()) {
            throw new ApiRequestException("esse id " + id + " não foi encontrado");
        }

        return sectorExist.get();

    }

    protected Employee employeeExist(Long id) {
        Optional<Employee> find = this.employeeRepository.findById(id);

        if (find.isEmpty()) {
            throw new ApiRequestException("esse id " + id + " não foi encontrado");
        }

        return find.get();

    }

    protected void checkIsCPFUpdateAndExist(String actualCPF, String updateCPF) {
        if (!actualCPF.equals(updateCPF)) {
            var find = this.employeeRepository.findByCPF(updateCPF);

            if (find.isPresent()) {
                throw new ApiRequestException("esse cpf " + updateCPF + " ja esta registrado");
            }
        }
    }
    protected void cpfIsValid(String cpf) {
        if (!this.checkIsOnlyNumbers(cpf)) {
            throw new ApiRequestException("CPF deve ter 11 digitos");
        }
    }


    protected static boolean checkIsOnlyNumbers(String str) {
        String regex = "[0-9]+";

        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(str);

        return m.matches();
    }

    protected void throwErrorIfEmploymentHasFound(String name) {
        Optional<Employment> find = this.employmentRepository.findByName(name);

        if(find.isPresent()) {
            throw new ApiRequestException("esse cargo já foi registrado");
        }
    }

    protected void throwErrorIfSectorNotFound(Long id) {
        Optional<Sector> find = this.sectorRepository.findById(id);

        if(find.isEmpty()) {
            throw new ApiRequestException("esse id " + id + " do setor não foi encontrado");
        }
    }


    protected Employment throwErrorIfEmploymentNameNotFound(String name) {
        Optional<Employment> find = this.employmentRepository.findByName(name);

        if(find.isEmpty()) {
            throw new ApiRequestException("nenhum cargo com esse nome " + name + " foi encontrado");
        }

        return find.get();
    }

}
