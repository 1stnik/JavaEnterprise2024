package com.hillel;

/**
 * Уявіть, що ви розробляєте систему управління баром, де клієнти можуть робити замовлення на напої,
 * а бармени готують ці напої. Створіть програму, яка симулює цю систему за допомогою потоків.
 * Створіть клас Bar, який буде представляти бар. У цьому класі створіть методи для:
 * Приймання замовлень від клієнтів.
 * Підготовки напоїв барменами.
 * Видачі готових напоїв клієнтам.
 * Створіть класи Client і Bartender, які реалізують інтерфейс Runnable.
 * Клас Client представляє клієнта, який робить замовлення.
 * Клас Bartender представляє бармена, який готує напої.
 * У методі run() кожного з цих класів реалізуйте відповідну логіку. Наприклад, клієнт може робити замовлення,
 * а бармен може приймати замовлення, готувати напій та видачу його клієнту.
 * Використайте механізм синхронізації для управління доступом до ресурсів (наприклад, замовлень і готових напоїв),
 * щоб уникнути гонки за ресурсами.
 * Створіть об'єкти клієнтів та барменів і запустіть їх у окремих потоках.
 * Виведіть повідомлення про кожну дію, що відбувається в програмі (наприклад, "Клієнт №1 замовив коктейль",
 * "Бармен приготував каву" і т. д.).
 */

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Bar {

    private final BlockingQueue<Order> orders = new LinkedBlockingQueue<>();
    private final List<String> drinks = Arrays.asList("Beer", "Whisky", "Coffee", "Juice");

    public String getDrink() {
        return drinks.get(new Random().nextInt(drinks.size()));
    }

    public void takeOrder(String client, String drink) {
        System.out.println("Client " + client + " ordered " + drink);
        try {
            Order order = new Order(client, drink);
            orders.put(order);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Order drinkPreparation() throws InterruptedException {
        return orders.take();
    }
}


