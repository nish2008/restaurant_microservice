package com.solutions.restaurantservice.model;

public class Restaurant {
    private String name;
    private String id;
    private Menu menu;

    public Restaurant() {
    }

    public Restaurant(String name, String id, Menu menu) {
        this.name = name;
        this.id = id;
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
