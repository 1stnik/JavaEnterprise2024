package com.hillel;

public class Order {

    private final String client;

    private final String drink;


    public Order(String client, String drink) {
        this.client = client;
        this.drink = drink;
    }

    public String getClient() {
        return client;
    }

    public String getDrink() {
        return drink;
    }
}
