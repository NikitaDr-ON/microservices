package com.store.constructor.models;

public class Dealer {
    private long id;
    private String name;

    public Dealer() {
    }

    public Dealer(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return(this.getName() + this.getId());
    }
}
