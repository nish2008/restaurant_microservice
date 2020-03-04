package com.solutions.restaurantservice.model.priceService;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solutions.restaurantservice.model.itemService.Item;

import javax.persistence.*;

@Entity
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="item_amount")
    private int amount;

    @OneToOne
    @JoinColumn(name = "item_id")
    @JsonIgnore
    private Item item;

    public Price() {

    }

    public Price(int amount, Item item) {
        this.amount = amount;
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
