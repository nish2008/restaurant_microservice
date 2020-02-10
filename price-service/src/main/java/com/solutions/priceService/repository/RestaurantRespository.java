package com.solutions.priceService.repository;

import com.solutions.priceService.model.restaurantservice.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRespository extends CrudRepository<Restaurant, Long> {
}
