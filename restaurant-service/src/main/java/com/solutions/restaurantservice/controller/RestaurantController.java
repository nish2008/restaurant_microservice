package com.solutions.restaurantservice.controller;

import com.solutions.restaurantservice.model.Restaurant;
import com.solutions.restaurantservice.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//http://localhost:8084/search/1
@RestController
@RequestMapping("/search")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/{restaurantName}")
    public Restaurant getByName(@PathVariable("restaurantName") Long id) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurant(id);
        return restaurant.get();
    }

    @PostMapping("/add-restaurant")
    public  Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.addRestaurant(restaurant);
    }

    @PutMapping("/update-restaurant")
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.updateRestaurant(restaurant);
    }
}

