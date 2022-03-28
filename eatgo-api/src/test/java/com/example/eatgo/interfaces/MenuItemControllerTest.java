package com.example.eatgo.interfaces;

import com.example.eatgo.application.MenuItemService;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MenuItemController.class)
public class MenuItemControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MenuItemService menuItemService;


    @DisplayName("1 . bulk update Menu")
    @Test
    void test_1() throws Exception {
//        given()
        mvc.perform(MockMvcRequestBuilders.patch("/restaurants/1/menuItems")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"name\":\"fried\"}]"))
                .andExpect(status().isOk())
        ;


        verify(menuItemService).bulkUpdate(eq(1L), any());


    }

}