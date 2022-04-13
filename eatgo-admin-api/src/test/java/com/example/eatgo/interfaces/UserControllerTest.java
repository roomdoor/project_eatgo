package com.example.eatgo.interfaces;

import com.example.eatgo.application.UserService;
import com.example.eatgo.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.RequestResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @DisplayName("1. User list")
    @Test
    public void test_1() throws Exception {

        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .email("test@test.com")
                .name("TestName")
                .level(1)
                .build());

        given(userService.getUsers()).willReturn(users);

        mvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("TestName")));
    }

    @DisplayName("2. create")
    @Test
    public void test_2() throws Exception {
        String email = "admin@test.com";
        String name = "adminName";
        User user = User.builder().name(name).email(email).build();
        given(userService.addUser(any())).willReturn(user);

        mvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"admin@test.com\",\"name\":\"adminName\"}"))
                .andExpect(status().isCreated())
                ;
        verify(userService).addUser(user);
    }

    @DisplayName("3. update User")
    @Test
    public void test_3() throws Exception {
        String email = "admin@test.com";
        String name = "updateName";
        Integer level = 100;
        User user = User.builder().name(name).email(email).level(level).build();
        given(userService.updateUser(100L, user)).willReturn(user);

        mvc.perform(MockMvcRequestBuilders.patch("/users/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"admin@test.com\",\"name\":\"updateName\",\"level\":100}"))
                .andExpect(status().isOk());

        verify(userService).updateUser(100L, user);
    }

    @DisplayName("4. delete User")
    @Test
    public void test_4() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/users/100"))
                .andExpect(status().isOk());

        verify(userService).deleteUser(100L);
    }
}