package com.example.eatgo.service;

import com.example.eatgo.domain.Menu;
import com.example.eatgo.repository.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MenuServiceTest {

    private MenuService menuService;

    @Mock
    private MenuRepository menuRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        menuService = new MenuService(menuRepository);
    }

    @DisplayName("1. bulkAdd")
    @Test
    void test_1() {
        List<Menu> menus = new ArrayList<>();
        menus.add(Menu.builder()
                .restaurantId(1L)
                .menuName("fried")
                .build());

        menus.add(Menu.builder()
                .restaurantId(1L)
                .menuName("soy")
                .build());

        menuService.menuBulkAdd(1L, menus);

        verify(menuRepository, times(2)).save(any());
    }


}