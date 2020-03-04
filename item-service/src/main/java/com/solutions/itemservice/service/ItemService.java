package com.solutions.itemservice.service;

import com.solutions.itemservice.model.Item;
import com.solutions.itemservice.model.priceservice.Price;
import com.solutions.itemservice.model.restaurantservice.Restaurant;
import com.solutions.itemservice.repository.ItemRespository;
import com.solutions.itemservice.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    ItemRespository itemRespository;

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    WebClient.Builder webClientBuilder;

    @Autowired
    RestTemplate restTemplate;

    //Service : Its responsible to provide all items by getting the restaurant id.
    public List<Item> getItems(Long id) {
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

    public List<Item> createItems(List<Item> items) {
        List<Item> itemsWithId = (List<Item>) itemRespository.saveAll(items);
        List<Price> prices = itemsWithId.stream().map(i->new Price(i.getPrice(),i)).collect(Collectors.toList());

        //restTemplate.postForObject("http://price-service/priceservice/addprices",prices,List.class);

        List<Price> pricesWithId = webClientBuilder.build()
                .post()
                .uri("http://price-service/priceservice/addprices")
                .body(BodyInserters.fromPublisher(Mono.just(prices),List.class))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
                .retrieve()
                .bodyToMono(List.class)
                .block();
        // Simply returning the list of items just to check directly from http://localhost:8083/itemservice/additems
        return (List<Item>)itemRespository.findAll();
    }

    public List<Item> updateItems(List<Item> items) {
        itemRespository.saveAll(items);
        List<Price> prices = new ArrayList<>();
        items.forEach(i -> {

            Price price = webClientBuilder.build().get().uri("http://price-service/priceservice/"+i.getId()).
                    header(HttpHeaders.CONTENT_TYPE,"application/json").
                    header(HttpHeaders.CONTENT_TYPE,"Spring 5 WebClient").retrieve().bodyToMono(Price.class).block();


            //Price price = priceRepository.findPriceByItem_Id(i.getId());
            price.setAmount(i.getPrice());
            prices.add(price);
        });

        List<Price> pricesWithId = webClientBuilder.build()
                .put()
                .uri("http://price-service/priceservice/updateprices")
                .body(BodyInserters.fromPublisher(Mono.just(prices),List.class))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
                .retrieve()
                .bodyToMono(List.class)
                .block();
        return (List<Item>)itemRespository.findAll();
    }
}
