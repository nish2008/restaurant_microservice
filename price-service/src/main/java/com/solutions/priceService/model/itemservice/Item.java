package com.solutions.priceService.model.itemservice;

import com.fasterxml.jackson.annotation.*;
import com.solutions.priceService.model.restaurantservice.Restaurant;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name")
    private String name;

    private String description;

    @Transient
    private int price;

    @JsonIgnoreProperties("items")
    @ManyToMany()
    private List<Restaurant> restaurants = new ArrayList<>();
    public Item() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Item(String name, String description, List<Restaurant> restaurants) {
        this.name = name;
        this.description = description;
        this.restaurants = restaurants;
    }
    /*
    public Item(String name, String description, Price price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
