package com.example.eatgo.application;

import com.example.eatgo.domain.MenuItem;
import com.example.eatgo.domain.MenuItemRepository;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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
   public void test_1() {
        List<MenuItem> menuItems = new ArrayList<>();

        menuItems.add(MenuItem.builder()
                .id(1L)
                .name("fried")
                .build());
        menuItems.add(MenuItem.builder()
                .id(2L)
                .name("soy")
                .build());

        menuItems.add(MenuItem.builder()
                .id(2L)
                .name("soy2")
                .build());

        menuItems.add(MenuItem.builder()
                .id(3L)
                .name("seasoning")
                .build());

        menuItems.add(MenuItem.builder()
                .id(3L)
                .name("seasoning")
                .remove(true)
                .build());

        menuItemService.bulkUpdate(1L, menuItems);


        verify(menuItemRepository, times(4)).save(any());
        verify(menuItemRepository, times(1)).deleteById(any());

    }

    @DisplayName("2. getMenuItems")
    @Test
    public void test_2(){
        given(menuItemRepository.findAllByRestaurantId(100L)).willReturn(List.of(MenuItem.builder()
                .name("fried").build()));
        List<MenuItem> menuItems = menuItemService.getMenuItems(100L);

        MenuItem menuItem = menuItems.get(0);

        assertThat(menuItem.getName(), Is.is("fried"));
    }

}