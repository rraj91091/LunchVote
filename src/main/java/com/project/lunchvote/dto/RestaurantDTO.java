package com.project.lunchvote.dto;

public class RestaurantDTO {

    private final String name;

    public RestaurantDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}