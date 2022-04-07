package com.example.eatgo.interfaces;

import com.example.eatgo.application.MenuItemService;
import com.example.eatgo.domain.MenuItem;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("/restaurants/{restaurantId}/menuItems")
    public List<MenuItem> getMenuItems(@PathVariable("restaurantId") Long restaurantId) {
        return menuItemService.getMenuItems(restaurantId);
    }


    @PatchMapping("/restaurants/{restaurantId}/menuItems")
    public String updateMenu(@PathVariable("restaurantId") Long restaurantId,
                             @RequestBody List<MenuItem> menuItems) {

        menuItemService.bulkUpdate(restaurantId, menuItems);
        return "[]";
    }
}
