package com.example.eatgo.application;

import com.example.eatgo.domain.Region;
import com.example.eatgo.domain.RegionRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class RegionServiceTest {

    @Mock
    private RegionRepository regionRepository;

    private RegionService regionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        regionService = new RegionService(regionRepository);

        List<Region> regions = new ArrayList<>();
        Region region = Region.builder().name("Seoul").build();
        regions.add(region);

        given(regionRepository.findAll()).willReturn(regions);
    }

    @DisplayName("1. All Region")
    @Test
    public void test1() {
        List<Region> regions = regionRepository.findAll();

        Region region = regions.get(0);

        MatcherAssert.assertThat(region.getName(), Is.is("Seoul"));
    }

    @DisplayName("add Region")
    @Test
    public void test_2() {
        Region region = regionService.addRegion("Seoul");

        verify(regionRepository).save(any());

        MatcherAssert.assertThat(region.getName(), Is.is("Seoul"));
    }
}