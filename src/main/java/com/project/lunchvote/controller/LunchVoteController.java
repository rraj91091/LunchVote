package com.project.lunchvote.controller;

import com.project.lunchvote.dto.RestaurantDTO;
import com.project.lunchvote.service.LunchVoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/restaurants")
public class LunchVoteController {

    private final LunchVoteService lunchVoteService;

    public LunchVoteController(LunchVoteService lunchVoteService) {
        this.lunchVoteService = lunchVoteService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void voteForRestaurant(@RequestParam("restaurant") String restaurant) {
        lunchVoteService.addRestaurantChoice(restaurant);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<RestaurantDTO> getRandomRestaurant() {
        String randomRestaurant = lunchVoteService.getRandomRestaurant();
        return new ResponseEntity<RestaurantDTO>(new RestaurantDTO(randomRestaurant), HttpStatus.OK);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void removeRestaurantChoices() {
        lunchVoteService.removeAllRestaurants();
    }

}