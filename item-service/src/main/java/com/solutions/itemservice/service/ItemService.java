package com.solutions.itemservice.service;

import com.solutions.itemservice.model.Item;
import com.solutions.itemservice.model.priceservice.Price;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/itemservice")
public class ItemService {

    @GetMapping("/{id}")
    public ArrayList<Item> getItemsById(@PathVariable("id") String id){
        return new ArrayList<Item>(Arrays.asList(new Item("I1","CLA CLA",new Price(100))));
    }
}
