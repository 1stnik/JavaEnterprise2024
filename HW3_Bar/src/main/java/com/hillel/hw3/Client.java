package com.hillel.hw3;

public class Client implements Runnable {

    private final Bar bar;
    private final String name;

    public Client(Bar bar, String name) {
        this.bar = bar;
        this.name = name;
    }


    @Override
    public void run() {
        String drink = bar.getDrink();
        bar.takeOrder(name, drink);
    }
}
