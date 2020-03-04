package com.solutions.restaurantservice.service;

import com.solutions.restaurantservice.model.Restaurant;
import com.solutions.restaurantservice.model.itemService.Item;
import com.solutions.restaurantservice.repository.RestaurantRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;


@Service
public class RestaurantService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WebClient.Builder webClientBuilder;

    @Autowired
    RestaurantRespository restaurantRespository;

    public Optional<Restaurant> getRestaurant(Long id) {

        //1. Very first step hardcoded
        //return new Restaurant("R1","BLA BLA",Arrays.asList(new Item("I1","CLA CLA",new Price(100)))));

        //2. Second approach using RestTemplate but its make synchronous calls and it uses servlet based technology to deal with request and hence end's up in exhausting memory taken each thread.
        //ArrayList<Item> items = restTemplate.getForObject("http://localhost:8083/itemservice/foo", ArrayList.class);

        //3. Third approach using webclient which make asynchronous calls coz it creates the task for each event and put the tasks in the queue. It execute the task only when response is available.
        /*WebClient webClient = WebClient.builder().
                baseUrl("http://localhost:8083").
                defaultHeader(HttpHeaders.CONTENT_TYPE,"application/json").
                defaultHeader(HttpHeaders.CONTENT_TYPE,"Spring 5 WebClient").
                build();

        ArrayList<Item> items = webClient.get().uri("/itemservice/foo").retrieve().bodyToMono(ArrayList.class).block();*/

        //4. Draw back in above approach is we are using hardcoded urls, but when we use spring cloud we may get different url, so to accommodate this requirement we can use Discovery Service.
        //Concept behind the discovery service is all services has to register their urls to discovery service and client will communicate to discovery server to get the actual URL(host).
        //After that client will make calls to the required service. This concept known as client_side_discovery. In this design there is flaw client has ro work a lot.But spring cloud uses this approach with the help of EUREKA EUREKA EUREKA.
        //Eureka library provided by NETFLIX.  Finally we will be dealing with Eureka client and Eureka Server.
        //Every Eureka server is Eureka Client.

        //To come up from client_side_discovery flaw we will be using server_side_discovery in which discovery server will directly talk to the service instead of client. So we can see we have reduce on step.

        //4. Eureka client with webclient


        Optional<Restaurant> restaurant = restaurantRespository.findById(id);
        ArrayList<Item> items = webClientBuilder.build().get().uri("http://item-service/itemservice/"+restaurant.get().getId()).
                header(HttpHeaders.CONTENT_TYPE,"application/json").
                header(HttpHeaders.CONTENT_TYPE,"Spring 5 WebClient").retrieve().bodyToMono(ArrayList.class).block();
        restaurant.get().setItems(items);
        return restaurant;
    }

    public  Restaurant addRestaurant(Restaurant restaurant) {
        List<Item> items = restaurant.getItems();
        Restaurant restaurantWithId = restaurantRespository.save(restaurant);
        items.forEach(i->i.setRestaurants(Arrays.asList(restaurantWithId)));
        //restTemplate.postForObject("http://item-service/itemservice/additems",items,List.class);


       List<Item> items1 = webClientBuilder
                .build()
               .post()
               .uri("http://item-service/itemservice/additems")
               .body(BodyInserters.fromPublisher(Mono.just(items), List.class))
               .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
               .header(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
               .retrieve()
               .bodyToMono(List.class)
               .block();

        return this.getRestaurant(restaurantWithId.getId()).get();
    }

    public  Restaurant updateRestaurant(Restaurant restaurant) {
        List<Item> items = restaurant.getItems();
        Restaurant restaurantWithId = restaurantRespository.save(restaurant);
        items.forEach(i->i.setRestaurants(Arrays.asList(restaurantWithId)));
        //restTemplate.postForObject("http://item-service/itemservice/additems",items,List.class);


        List<Item> items1 = webClientBuilder
                .build()
                .put()
                .uri("http://item-service/itemservice/updateitems")
                .body(BodyInserters.fromPublisher(Mono.just(items), List.class))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
                .retrieve()
                .bodyToMono(List.class)
                .block();

        return this.getRestaurant(restaurantWithId.getId()).get();
    }

}
