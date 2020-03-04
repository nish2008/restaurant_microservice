package com.solutions.restaurantservice;

import com.solutions.restaurantservice.model.Restaurant;
import com.solutions.restaurantservice.model.itemService.Item;
import com.solutions.restaurantservice.model.priceService.Price;
import com.solutions.restaurantservice.repository.ItemRespository;
import com.solutions.restaurantservice.repository.PriceRepository;
import com.solutions.restaurantservice.repository.RestaurantRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
@EnableEurekaClient
public class RestaurantServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantServiceApplication.class, args);
	}


	@Bean
    @LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@Bean
    @LoadBalanced
    public WebClient.Builder getWebClient(){
	    return WebClient.builder();
    }

    @Bean
	public CommandLineRunner getCommandLineRunner(PriceRepository priceRepository, ItemRespository itemRespository, RestaurantRespository restaurantRespository) {


		return (args) -> {
			Restaurant restaurant1 = new Restaurant("Eat.Fit");

			restaurantRespository.save(restaurant1);

			Item item1 = new Item("Chicken Biriyani", "Basmati Rice, Egg, Big Big Lollipops", Arrays.asList(restaurant1));
            Item item2 = new Item("Hakka Noddle", "Chicken Chunks, Smoody Noddles",Arrays.asList(restaurant1));
            Item item3 = new Item("Chicken Wrap", "Chicken , Mayonnaise, Fried Onion, Fried Tomatoes",Arrays.asList(restaurant1));
			itemRespository.saveAll(Arrays.asList(item1,item2,item3));

			Price price1 = new Price(100, item1);
            Price price2 = new Price(200, item2);
            Price price3 = new Price(300, item3);
			priceRepository.saveAll(Arrays.asList(price1,price2,price3));
		};
	}


}
