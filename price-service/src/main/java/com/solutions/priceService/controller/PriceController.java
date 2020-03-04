package com.solutions.priceService.controller;

import com.solutions.priceService.model.Price;
import com.solutions.priceService.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//http://localhost:8082/priceservice/2
@RestController
@RequestMapping("/priceservice")
public class PriceController {

    @Autowired
    PriceService priceService;

    @GetMapping("{id}")
    public Price getPriceByItemId(@PathVariable(value = "id") Long id){
        return priceService.getPrice(id);
    }

    @PostMapping("/addprice")
    public Price addPrice(@RequestBody Price price){
        return priceService.addPrice(price);
    }

    @PostMapping("/addprices")
    public List<Price> addPrice(@RequestBody List<Price> prices){
        return priceService.addPrices(prices);
    }

    @PutMapping("/updateprices")
    public List<Price> updatePrices(@RequestBody List<Price> prices) {
        return priceService.addPrices(prices);
    }
}
