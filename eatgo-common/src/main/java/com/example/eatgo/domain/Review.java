package com.example.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    private Long restaurantId;

    @NotEmpty
    private String name;

    @NotNull
    private Integer score;

    @NotEmpty
    private String comment;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean remove;
}
