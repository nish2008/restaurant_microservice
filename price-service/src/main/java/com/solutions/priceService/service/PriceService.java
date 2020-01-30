package com.solutions.priceService.service;

import com.solutions.priceService.model.Price;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/priceservice")
public class PriceService {

    @GetMapping("{id}")
    public Price getPriceByItemId(String id){
        return new Price(100);
    }
}
