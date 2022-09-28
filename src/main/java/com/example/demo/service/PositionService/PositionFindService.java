package com.example.demo.service.PositionService;

import com.example.demo.entities.Employee;
import com.example.demo.entities.Position;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PositionRepository;
import com.example.demo.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PositionFindService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private SectorRepository sectorRepository;


    public List<Position> findAll() {

        var find = this.positionRepository.findAll();
//
//        var size = find.size();
//
//        List<Position> positions = Arrays.asList(find.get(size));
//
        return find;
//        positions.forEach(p ->  {
//           var employee = this.employeeRepository.findByPositionId(p.getId());
//                if(employee.isPresent()) {
//                    System.out.print("===================");
//                    System.out.print(employee.get().getName());
//                }
//        });
//        find.stream().forEach(p -> {
//            var user = this.employeeRepository.findByPositionId(position.getId());
//
//            System.out.print("===================");
//
//            if (user.isPresent()) {
//                System.out.print(user);
//
//                System.out.print("===================");
//                System.out.print(user.get().getName());
//            }
//        });
    }

    public List<Employee> findByName(String name) {
        try {
            //retorna 1
            Optional<Position> positionExist = this.positionRepository.findByName(name);

            if (positionExist.isEmpty()) {
                throw new ApiRequestException("nenhum cargo com esse nome foi encontrado: " + name);
            }

            var itens = Arrays.asList(positionExist.get());

            List<Employee> getAllEmplooyes = this.employeeRepository.findByPositionIdAndSectorId(positionExist.get().getId(), positionExist.get().getSectorId());


            for (var item : getAllEmplooyes) {


            }

            return getAllEmplooyes;


        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
