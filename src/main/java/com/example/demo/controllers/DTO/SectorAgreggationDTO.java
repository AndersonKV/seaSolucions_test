package com.example.demo.controllers.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SectorAgreggationDTO {
    private Long id;
    private String sectorName;
    private List<SectorDTO> sectorList;

}
