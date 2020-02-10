package com.solutions.itemservice.model.restaurantservice;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.solutions.itemservice.model.Item;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    //@JsonBackReference      This annotation was required when we want bidirectional many to many.
    private Set<Item> items = new HashSet<>();

    public Restaurant() {
    }

    public Restaurant(String name) {
        this.name = name;
    }
    public Restaurant(Long id) {
        this.id = id;
    }

    public Restaurant(String name, Set<Item> items) {
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

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
