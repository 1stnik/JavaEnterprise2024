package com.hillel;


import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class Main {
    public static void main(String[] args) {

        Bar bar = new Bar();

        int numOfClients = 5;

        int numOfBartenders = 3;

        long barIsOpeningAt = System.currentTimeMillis();
        long duration = 10000;

        while (System.currentTimeMillis() - barIsOpeningAt < duration) {

            ExecutorService executorService = newFixedThreadPool(numOfClients + numOfBartenders);

            for (int i = 1; i <= numOfClients; i++) {
                Client client = new Client(bar, "Client" + i);
                executorService.submit(client);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            for (int i = 1; i <= numOfBartenders; i++) {
                Bartender bartender = new Bartender(bar, "Bartender" + i);
                executorService.submit(bartender);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            executorService.shutdown();
        }
        System.exit(0);
    }
}
