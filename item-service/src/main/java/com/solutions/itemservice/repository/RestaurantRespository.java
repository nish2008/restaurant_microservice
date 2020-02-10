package com.solutions.itemservice.repository;

import com.solutions.itemservice.model.restaurantservice.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRespository extends CrudRepository<Restaurant, Long> {
}
