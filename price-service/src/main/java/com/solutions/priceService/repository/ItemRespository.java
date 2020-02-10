package com.solutions.priceService.repository;

import com.solutions.priceService.model.itemservice.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRespository extends CrudRepository<Item, Long> {
}
