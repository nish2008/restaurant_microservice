package com.solutions.itemservice.model;


import com.solutions.itemservice.model.priceservice.Price;

public class Item {

    private String name;
    private String desc;
    private Price price;

    public Item() {
    }

    public Item(String name, String desc, Price price) {
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
