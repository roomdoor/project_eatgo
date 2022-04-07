package com.example.eatgo.domain;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class RegionTest {

    @DisplayName("1. region")
    @Test
    public void test_1(){
        Region region = Region.builder().name("서울").build();

        MatcherAssert.assertThat(region.getName(), Is.is("서울"));
    }
}