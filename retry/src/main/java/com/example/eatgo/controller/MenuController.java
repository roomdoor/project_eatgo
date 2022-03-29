package com.example.eatgo.controller;

import com.example.eatgo.domain.Menu;
import com.example.eatgo.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PatchMapping("/restaurants/{restaurantId}/menus")
    public String addMenuBulk(@PathVariable("restaurantId") Long restaurantId,
                              @RequestBody List<Menu> menus) {

        menuService.menuBulkAdd(restaurantId, menus);

        return "{}";
    }
}
