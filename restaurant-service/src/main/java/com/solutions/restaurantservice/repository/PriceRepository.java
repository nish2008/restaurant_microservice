package com.solutions.restaurantservice.repository;

import com.solutions.restaurantservice.model.priceService.Price;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {
}
