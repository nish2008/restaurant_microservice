package com.solutions.itemservice.repository;

import com.solutions.itemservice.model.Item;
import com.solutions.itemservice.model.restaurantservice.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRespository extends CrudRepository<Item, Long> {
    public List<Item> findDistinctItemsByRestaurants(Restaurant r1);
}
