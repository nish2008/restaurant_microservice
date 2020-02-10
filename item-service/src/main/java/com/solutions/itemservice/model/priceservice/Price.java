package com.solutions.itemservice.model.priceservice;


import com.solutions.itemservice.model.Item;

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
}
