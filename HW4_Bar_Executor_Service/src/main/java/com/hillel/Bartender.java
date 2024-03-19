package com.hillel;

public class Bartender implements Runnable {
    private final Bar bar;
    private final String name;

    public Bartender(Bar bar, String name) {
        this.bar = bar;
        this.name = name;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Order order = bar.drinkPreparation();
                System.out.println("The " + name + " serve for " + order.getClient() + " " + order.getDrink());
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println("The Bartender was interrupted!");
                throw new RuntimeException(e);
            }
        }

    }
}
