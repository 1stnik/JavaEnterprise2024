package com.hillel.hw3;

public class Bartender implements Runnable {

    private final Bar bar;
    private final String name;


    public Bartender(Bar bar, String name) {
        this.bar = bar;
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 1; i < 15; i++) {
            bar.drinkPreparation(name);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
