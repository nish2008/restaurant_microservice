package com.solutions.restaurantservice.model;

import com.solutions.restaurantservice.model.itemService.Item;

import java.util.ArrayList;

public class Menu {
    private ArrayList<Item> items;

    public Menu() {

    }

    public Menu(ArrayList<Item> items) {
        this.items = items;
    }

    public void setItems(ArrayList<Item> items) {

        this.items = items;
    }

    public ArrayList<Item> getItems() {

        return items;
    }
}
