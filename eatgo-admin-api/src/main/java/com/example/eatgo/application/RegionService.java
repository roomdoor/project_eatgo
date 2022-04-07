package com.example.eatgo.application;

import com.example.eatgo.domain.Region;
import com.example.eatgo.domain.RegionRepository;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> AllRegion() {
        return regionRepository.findAll();
    }

    public Region addRegion(String name) {
        Region region = Region.builder().name(name).build();
        regionRepository.save(region);
        return region;
    }
}
