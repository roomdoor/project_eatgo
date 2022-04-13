package com.example.eatgo.application;

import com.example.eatgo.domain.User;
import com.example.eatgo.domain.UserRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        userService = new UserService(userRepository);

        List<User> users = new ArrayList<>();
        User user = User.builder()
                .id(100L)
                .name("TestName")
                .email("test@test.com")
                .level(1)
                .build();

        users.add(user);

        given(userRepository.findAll()).willReturn(users);
        given(userRepository.findById(100L)).willReturn(Optional.of(user));
    }

    @DisplayName("1. getUsers")
    @Test
    public void test_1() {
        List<User> users = userService.getUsers();

        User user = users.get(0);

        MatcherAssert.assertThat(user.getName(), Is.is("TestName"));
    }

    @DisplayName("2. addUser")
    @Test
    public void test_2(){
        User user = User.builder()
                .name("TestName")
                .email("test@test.com")
                .level(1)
                .build();

        userService.addUser(user);

        verify(userRepository).save(user);
    }

    @DisplayName("3. update User")
    @Test
    public void test_3(){

        User user = User.builder()
                .id(100L)
                .name("updateName")
                .email("test@test.com")
                .level(1)
                .build();

        User updateUser = userService.updateUser(100L, user);

        verify(userRepository).findById(eq(100L));

        MatcherAssert.assertThat(updateUser.getName(), Is.is("updateName"));
    }

    @DisplayName("4. delete user")
    @Test
    public void test_4(){
        var user = userService.deleteUser(100L);

        verify(userRepository).findById(100L);

        MatcherAssert.assertThat(user.isAdmin(),Is.is(false));
        MatcherAssert.assertThat(user.isActive(),Is.is(false));

    }

}