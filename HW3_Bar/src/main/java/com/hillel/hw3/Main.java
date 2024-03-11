package com.hillel.hw3;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Bar bar = new Bar();

        int numOfClients = 5;

        int numOfBartenders = 3;


        long barIsOpeningAt = System.currentTimeMillis();
        long duration = 3000;

        while (System.currentTimeMillis() - barIsOpeningAt < duration) {
            List<Client> clientList = new ArrayList<>();
            for (int i = 1; i <= numOfClients; i++) {
                Client client = new Client(bar, "Client" + i);
                clientList.add(client);
                Thread clientThread = new Thread(client);
                clientThread.start();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }

            List<Bartender> bartenderList = new ArrayList<>();
            for (int i = 1; i <= numOfBartenders; i++) {
                Bartender bartender = new Bartender(bar, "Bartender" + i);
                bartenderList.add(bartender);
                Thread bartenderThread = new Thread(bartender);
                bartenderThread.start();
            }

        }

        System.exit(0);

    }
}
