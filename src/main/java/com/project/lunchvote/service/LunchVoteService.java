package com.project.lunchvote.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class LunchVoteService {

    public static final String NO_RESTAURANTS = "NIL";
    private final List<String> restaurants = new ArrayList<>();

    public void addRestaurantChoice(String restaurant) {
        restaurants.add(restaurant);
    }

    public String getRandomRestaurant() {
        if (restaurants.isEmpty()) {
            return NO_RESTAURANTS;
        } else {
            int randomChoice = new Random().nextInt(restaurants.size());
            return restaurants.get(randomChoice);
        }
    }

    public void removeAllRestaurants() {
        restaurants.clear();
    }
}
