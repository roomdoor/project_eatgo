package com.example.eatgo.application;

import com.example.eatgo.domain.MenuItem;
import com.example.eatgo.domain.MenuItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class MenuItemServiceTest {

    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        menuItemService = new MenuItemService(menuItemRepository);
    }


    @DisplayName("1. bulk update")
    @Test
    void test_1() {
        List<MenuItem> menuItems = new ArrayList<>();

        menuItems.add(MenuItem.builder()
                .id(1L)
                .name("fried")
                .build());
        menuItems.add(MenuItem.builder()
                .name("soy")
                .build());

        menuItems.add(MenuItem.builder()
                .name("soy")
                .build());


        menuItemService.bulkUpdate(1L, menuItems);


        verify(menuItemRepository, times(3)).save(any());
//        verify(menuItemRepository,times(1)).deleteById(any());

    }

}