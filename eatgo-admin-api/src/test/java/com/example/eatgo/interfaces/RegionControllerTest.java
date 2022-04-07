package com.example.eatgo.interfaces;

import com.example.eatgo.application.RegionService;
import com.example.eatgo.domain.Region;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RegionController.class)
public class RegionControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private RegionService regionService;

    @DisplayName("1. region list")
    @Test
    public void test_1() throws Exception {
        List<Region> regions = new ArrayList<>();
        regions.add(Region.builder().name("Seoul").build());
        given(regionService.AllRegion()).willReturn(regions);
        mvc.perform(MockMvcRequestBuilders.get("/regions"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Seoul")))
        ;
    }


    @DisplayName("2. add Region")
    @Test
    public void test_2() throws Exception {

        Region region = Region.builder().name("Seoul").build();
        given(regionService.addRegion(any())).willReturn(region);
        mvc.perform(MockMvcRequestBuilders.post("/regions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Seoul\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("{}")))
        ;

        verify(regionService).addRegion(any());
    }
}