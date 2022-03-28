package com.example.eatgo.domain;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(Long id) {
        super("레스토랑 id : " + id + "(을)를 찾을 수 없습니다");
    }
}
