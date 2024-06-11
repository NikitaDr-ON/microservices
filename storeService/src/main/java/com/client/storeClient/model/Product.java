package com.client.storeClient.model;

import java.util.ArrayList;

public class Product {
    private long id;
    private long characteristicsId;

    private  long dealerId;
    private String dealer;
    private String name;
    private int price;

    private String category;

    private ArrayList<Characteristics> characteristics;

    public Product() {
    }

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getCharacteristicsId() {
        return characteristicsId;
    }

    public void setCharacteristicsId(Long characteristicsId) {
        this.characteristicsId = characteristicsId;
    }

    public ArrayList<Characteristics> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(ArrayList<Characteristics> characteristics) {
        this.characteristics = characteristics;
    }

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
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

    public long getDealerId() {
        return dealerId;
    }

    public void setDealerId(long dealerId) {
        this.dealerId = dealerId;
    }

    @Override
    public String toString() {
        return (getName() + " " + getPrice() +" " + getDealer() + " "  + getCategory());
    }
}
