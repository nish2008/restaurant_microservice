package com.solutions.itemservice.service;

import com.solutions.itemservice.model.Item;
import com.solutions.itemservice.model.priceservice.Price;
import com.solutions.itemservice.model.restaurantservice.Restaurant;
import com.solutions.itemservice.repository.ItemRespository;
import com.solutions.itemservice.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

//http://localhost:8083/itemservice/1
@RestController
@RequestMapping("/itemservice")
public class ItemService {

    @Autowired
    ItemRespository itemRespository;

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    WebClient.Builder webClientBuilder;

    //Service : Its responsible to provide all items by getting the restaurant id.
    @GetMapping("/{id}")
    public List<Item> getItemsByRestaurantId(@PathVariable("id") Long id){

        List<Item> items =  itemRespository.findDistinctItemsByRestaurants(new Restaurant(id));
        items.forEach(i -> {

            Price price = webClientBuilder.build().get().uri("http://price-service/priceservice/"+i.getId()).
                    header(HttpHeaders.CONTENT_TYPE,"application/json").
                    header(HttpHeaders.CONTENT_TYPE,"Spring 5 WebClient").retrieve().bodyToMono(Price.class).block();


            //Price price = priceRepository.findPriceByItem_Id(i.getId());
        i.setPrice(price.getAmount());
        });
        return items;
    }
}
