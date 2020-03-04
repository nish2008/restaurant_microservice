package com.solutions.priceService.service;

import com.solutions.priceService.model.Price;
import com.solutions.priceService.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {

    @Autowired
    PriceRepository priceRepository;

    public Price getPrice(Long id) {
        //Long itemId = Long.valueOf(id);
        return priceRepository.findPriceByItem_Id(id);
    }

    public Price addPrice(Price price) {
        return priceRepository.save(price);
    }

    public List<Price> addPrices(List<Price> prices) {
        return (List<Price>) priceRepository.saveAll(prices);
    }

}
