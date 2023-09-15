package com.project.lunchvote.controller;

import com.project.lunchvote.service.LunchVoteService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LunchVoteControllerTests {

    @Autowired
    private LunchVoteService lunchVoteService;

    @Autowired
    private WebTestClient webTestClient;

    private final String apiEndpoint = "/v1/restaurants";

    @BeforeAll
    public void setup() {
        assertNotNull(webTestClient, "Warning: webTestClient is null!");
    }

    @BeforeEach
    public void clearData() {
        lunchVoteService.removeAllRestaurants();
    }

    @Test
    public void postRestaurants_should_add_restaurant_to_choice_list_and_return_accepted_status() {
        String restaurant = "HawkerChan";
        String urlWithRestaurantRequestParam = apiEndpoint + "?restaurant=" + restaurant;

        webTestClient.post().uri(urlWithRestaurantRequestParam)
                .exchange()
                .expectStatus().isAccepted()
                .expectBody().isEmpty();

        assertThat(lunchVoteService.getRandomRestaurant()).isEqualTo(restaurant);
    }

    @Test
    public void given_atLeast_one_restaurant_in_choice_list_getRestaurants_should_return_one_random_restaurant() {
        String restaurant = "Sushi-Tei";
        givenOneRestaurantAdded(restaurant);

        webTestClient.get().uri(apiEndpoint)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo(restaurant);
    }

    @Test
    public void given_no_restaurants_in_choice_list_getRestaurants_should_return_NIL_message() {
        webTestClient.get().uri(apiEndpoint)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo(LunchVoteService.NO_RESTAURANTS);
    }

    @Test
    public void deleteRestaurants_should_remove_all_restaurants_in_the_choice_list() {
        givenOneRestaurantAdded("random-restaurant");

        webTestClient.delete().uri(apiEndpoint)
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();

        assertThat(lunchVoteService.getRandomRestaurant()).isEqualTo(LunchVoteService.NO_RESTAURANTS);
    }

    private void givenOneRestaurantAdded(String restaurant) {
        String urlWithRestaurantRequestParam = apiEndpoint + "?restaurant=" + restaurant;
        webTestClient.post().uri(urlWithRestaurantRequestParam)
                .exchange()
                .expectStatus().isAccepted()
                .expectBody().isEmpty();
    }

}