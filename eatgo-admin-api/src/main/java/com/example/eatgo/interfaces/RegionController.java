package com.example.eatgo.interfaces;

import com.example.eatgo.application.RegionService;
import com.example.eatgo.domain.Region;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping("/regions")
    public List<Region> list() {
        return regionService.AllRegion();
    }

    @PostMapping("/regions")
    public ResponseEntity<?> addRegion(@RequestBody String name) throws URISyntaxException {
        Region region = regionService.addRegion(name);

        URI uri = new URI("/regions/" + region.getId());
        return ResponseEntity.created(uri).body("{}");
    }
}

