package com.example.eatgo.interfaces;

import com.example.eatgo.application.MenuItemService;
import com.example.eatgo.domain.MenuItem;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MenuItemController.class)
public class MenuItemControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MenuItemService menuItemService;


    @DisplayName("1 . bulk update Menu")
    @Test
    public void test_1() throws Exception {

        mvc.perform(MockMvcRequestBuilders.patch("/restaurants/1/menuItems")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"name\":\"fried\"}]"))
                .andExpect(status().isOk())
        ;


        verify(menuItemService).bulkUpdate(eq(1L), any());
    }

    @DisplayName("2. getMenuItems")
    @Test
    public void test_2() throws Exception {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder().name("fried").build());
        given(menuItemService.getMenuItems(1L)).willReturn(menuItems);

        mvc.perform(MockMvcRequestBuilders.get("/restaurants/1/menuItems"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("fried")))
        ;
    }
}