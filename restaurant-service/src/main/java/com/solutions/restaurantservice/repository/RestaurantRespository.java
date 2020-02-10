package com.solutions.restaurantservice.repository;

import com.solutions.restaurantservice.model.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRespository extends CrudRepository<Restaurant, Long> {
}
