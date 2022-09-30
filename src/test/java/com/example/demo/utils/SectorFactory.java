package com.example.demo.utils;

import com.example.demo.entities.Sector;

public class SectorFactory {

    public Sector create(String name) {
        Sector sector = new Sector();
        sector.setSectorName(name);
        return sector;
    }

    public Sector update(Long id, String name) {
        Sector sector = new Sector();
        sector.setId(id);
        sector.setSectorName(name);
        return sector;
    }

}
