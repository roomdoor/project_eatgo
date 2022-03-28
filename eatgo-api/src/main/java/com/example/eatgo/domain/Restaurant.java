package com.example.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    @Setter
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MenuItem> menuItems;

    public String getInformation() {
        return name + " in " + address;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>(menuItems);
    }

    public void updateRestaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
