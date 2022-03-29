package com.example.eatgo.controller;

import com.example.eatgo.service.MenuService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MenuController.class)
public class MenuControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MenuService menuService;

    @DisplayName("1. bulkAdd")
    @Test
    void test_1() throws Exception {

        mvc.perform(MockMvcRequestBuilders.patch("/restaurants/1/menus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[ {\n" +
                                "    \"restaurantId\": 1,\n" +
                                "    \"name\": \"fried\"\n" +
                                "  },\n" +
                                "  {\n" +
                                "    \"restaurantId\": 1,\n" +
                                "    \"name\": \"fried\"\n" +
                                "  }]"))
                .andExpect(status().isOk());

        verify(menuService, times(1)).menuBulkAdd(eq(1L), any());
    }

}