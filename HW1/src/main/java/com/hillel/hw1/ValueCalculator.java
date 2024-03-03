package com.hillel.hw1;

import java.util.Arrays;

/**
 * HW_1 (Value Calculator)
 * 1. Створити клас ValueCalculator
 * 2. У класі ValueCalculator додати:
 * - властивість-масив із дійсних чисел
 * - властивість, що відображає розмір масиву (мінімально 1 000 000)
 * - властивість, що відображає половину розміру масиву
 * 3. У класі ValueCalculator додати метод Метод виконує:
 * - Зафіксувати час старту виконання методу: long start = System.currentTimeMillis()
 * - Заповнити масив одиницями або будь-якими іншими однаковими значеннями
 * - Розбити масив на два масиви однакової величини:
 * Приклад розподілу одного масиву на два:
 * System.arraycopy(arr, 0 , a1, 0, half);
 * System.arraycopy(arr, half, a2, 0, half);
 * - Створити два потоки, у кожному з яких пройтися одним із раніше отриманих масивів.
 * Присвоїти його значенням нові значення, сформовані виразом:
 * (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2))
 * - Виконати зворотне склеювання двох масивів в один початковий
 * Приклад зворотного склеювання:
 * System.arraycopy(a1, 0, arr, 0, h);
 * System.arraycopy(a2, 0, arr, h, h);
 * - Обчислити витрачений час від старту до завершення програми та виведення його в консоль з використанням двох
 * потоків та з використанням одного потоку
 * *Зробити шафлінг даних в масиві (перемешати випадковим чином, щоб значення були невідсортовані)
 * *Додати логіку, яка шукає найбільший елемент у заданому масиві
 * Зробити це через декілька потоків (кількість потоків обрати самім, наприклад зробити через 2, 5 та 10 потоків
 * й порівняти я якою кількістю потоків программа найшвидше знайшла максимальне значення)
 */


public class ValueCalculator {

    private double[] srcArray;
    private int size;
    private int halfSize;


    public ValueCalculator(int size) {
        this.size = Math.max(size, 1_000_000);
        this.halfSize = size / 2;
        this.srcArray = new double[this.size];
    }

    public void calculate() throws InterruptedException {

        long start = System.currentTimeMillis();

        Arrays.fill(srcArray, 1.0);

        double[] firstPartArray = new double[halfSize];
        double[] secondPartArray = new double[halfSize];

        System.arraycopy(srcArray, 0 , firstPartArray, 0, halfSize);
        System.arraycopy(srcArray, halfSize , secondPartArray, 0, halfSize);

        Thread thread1 = new Thread(() -> arrayChanger(firstPartArray));
        Thread thread2 = new Thread(() -> arrayChanger(secondPartArray));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.arraycopy(firstPartArray, 0, srcArray, 0, halfSize);

        System.arraycopy(secondPartArray, 0, srcArray, halfSize, halfSize);

        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println("Elapsed time with 2 threads: " + elapsedTime);

        // --------------------------------------------------------------

        start = System.currentTimeMillis();
        Arrays.fill(srcArray, 1.0);
        arrayChanger(srcArray);
        end = System.currentTimeMillis();
        elapsedTime = end - start;
        System.out.println("Elapsed time with 1 thread: " + elapsedTime);

    }

    public void arrayChanger(double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    public static void main(String[] args) {
        ValueCalculator valueCalculator = new ValueCalculator(1_000_000);

        try {
            valueCalculator.calculate();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        for (int i = 0; i < valueCalculator.srcArray.length; i++) {
//            System.out.println(valueCalculator.srcArray[i]);
//        }
    }
}