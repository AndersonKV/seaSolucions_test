package com.example.demo.DTO;

import com.example.demo.DTO.EmployeeDTO.EmploymentAggregationDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SectorDTO {
    private Long id;
//    @NotEmpty(message = "nome do setor não pode estar vazio")
//    private String sectorName;

    // private List<ListSectorDTO> sectorList;
    private String sectorName;
    private List<EmploymentAggregationDTO> list;

    public SectorDTO() {

    }

    public SectorDTO(Long id, String sectorName, List<EmploymentAggregationDTO> list) {
        this.id = id;
        this.sectorName = sectorName;
        this.list = list;
    }
}
