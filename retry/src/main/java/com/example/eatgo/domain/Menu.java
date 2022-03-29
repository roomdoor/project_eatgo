package com.example.eatgo.domain;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Menu {

    @Id
    @GeneratedValue
    private Long id;

    private String menuName;

    private Long restaurantId;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean remove;

    public Menu(String menuName) {
        this.menuName = menuName;
    }
}
