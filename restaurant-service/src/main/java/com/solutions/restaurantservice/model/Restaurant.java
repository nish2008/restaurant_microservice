package com.solutions.restaurantservice.model;

import com.fasterxml.jackson.annotation.*;
import com.solutions.restaurantservice.model.itemService.Item;

import javax.persistence.*;
import java.util.List;

@Entity
public class Restaurant {


    @Column(name="restaurant_name")
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties("restaurants")
    @ManyToMany(mappedBy = "restaurants")
    private List<Item> items;

    public Restaurant() {
    }

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(String name, List<Item> items) {
        this.name = name;
        this.items = items;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
