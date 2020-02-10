package com.solutions.priceService.model.restaurantservice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.solutions.priceService.model.itemservice.Item;

import javax.persistence.*;
import java.util.List;

@Entity
public class Restaurant {


    @Column(name="restaurant_name")
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonInclude()
    @Transient
    private Menu menu;

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

    public Restaurant(String name, Menu menu) {
        this.name = name;
        this.menu = menu;
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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
