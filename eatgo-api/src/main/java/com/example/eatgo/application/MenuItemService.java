package com.example.eatgo.application;

import com.example.eatgo.domain.MenuItem;
import com.example.eatgo.domain.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MenuItemService {

    public final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {
        for (MenuItem menuItem : menuItems) {
            menuItem.setRestaurantId(restaurantId);
            menuItemRepository.save(menuItem);
        }
    }
}
