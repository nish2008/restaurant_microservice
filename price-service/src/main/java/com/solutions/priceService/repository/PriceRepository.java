package com.solutions.priceService.repository;

import com.solutions.priceService.model.Price;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {
    public Price findPriceByItem_Id(Long itemId);
}
