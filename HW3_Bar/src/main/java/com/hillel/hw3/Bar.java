package com.hillel.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Bar {

    private final List<String> orders = new ArrayList<>();

    private final List<String> drinks = Arrays.asList("Beer", "Whisky", "Coffee", "Juice");


    public synchronized String getDrink() {
        return drinks.get(new Random().nextInt(drinks.size()));
    }


    public synchronized void takeOrder(String client, String drink) {
        System.out.println("Client " + client + " ordered " + drink);
        orders.add(drink);
        notifyAll();
    }

    public synchronized void drinkPreparation(String bartender) {
        while (orders.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        String drink = orders.remove(0);
        System.out.println("The " + bartender + " served a drink: " + drink);
    }


}
