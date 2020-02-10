package com.solutions.priceService.service;

import com.solutions.priceService.model.Price;
import com.solutions.priceService.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//http://localhost:8082/priceservice/2
@RestController
@RequestMapping("/priceservice")
public class PriceService {

    @Autowired
    PriceRepository priceRepository;

    @GetMapping("{id}")
    public Price getPriceByItemId(@PathVariable(value = "id") Long id){
        //Long itemId = Long.valueOf(id);
        return priceRepository.findPriceByItem_Id(id);
    }
}
