package com.solutions.itemservice.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.solutions.itemservice.model.priceservice.Price;
import com.solutions.itemservice.model.restaurantservice.Restaurant;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(name = "menu")
    //@JsonManagedReference
    @JsonIgnore
    private Set<Restaurant> restaurants = new HashSet<>();

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

   /* public Item(String name, String description, Set<Restaurant> restaurants) {
        this.name = name;
        this.description = description;
        this.restaurants = restaurants;
    }

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

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
