package com.example.eatgo.service;

import com.example.eatgo.domain.Menu;
import com.example.eatgo.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    public final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void menuBulkAdd(Long restaurantId, List<Menu> menus) {
        for (Menu menu : menus) {
            if (menu.isRemove()) {
                menuRepository.deleteById(restaurantId);
                continue;
            }

            menu.setRestaurantId(restaurantId);
            menuRepository.save(menu);
        }

    }


}
