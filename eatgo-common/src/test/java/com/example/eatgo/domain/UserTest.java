package com.example.eatgo.domain;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class UserTest {

    @DisplayName("1. creation")
    @Test
    public void test_1(){
        User user = User.builder()
                .email("test@test.com")
                .name("TestName")
                .level(100)
                .build();


        MatcherAssert.assertThat(user.getName(), Is.is("TestName"));
        MatcherAssert.assertThat(user.isAdmin(), Is.is(true));

    }

}