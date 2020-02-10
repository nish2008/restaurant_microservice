package com.solutions.restaurantservice.repository;

import com.solutions.restaurantservice.model.itemService.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRespository extends CrudRepository<Item, Long> {
}
