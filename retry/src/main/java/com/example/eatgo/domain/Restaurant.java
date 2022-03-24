package com.example.eatgo.domain;

import com.example.eatgo.repository.Menu;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Restaurant {

    private Long id;
    private String name;
    private String address;
    private List<Menu> menus = new ArrayList<>();

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getInfo() {
        return name + " in " + address;
    }

    public void addMenu(Menu menu) {
        menus.add(menu);
    }
}
