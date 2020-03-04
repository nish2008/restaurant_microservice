package com.solutions.itemservice.controller;

import com.solutions.itemservice.model.Item;
import com.solutions.itemservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8083/itemservice/1
@RestController
@RequestMapping("/itemservice")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/{id}")
    public List<Item> getItemsByRestaurantId(@PathVariable("id") Long id){

        List<Item> items = itemService.getItems(id);
        return items;
    }

    @PostMapping("/additems")
    public List<Item> addItems(@RequestBody List<Item> items) {
        return itemService.createItems(items);
    }

    @PutMapping("/updateitems")
    public List<Item> updateItems(@RequestBody List<Item> items) {
        return itemService.updateItems(items);
    }
}
