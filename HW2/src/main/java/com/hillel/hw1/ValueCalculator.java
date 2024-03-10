package com.hillel.hw1;

import java.util.Arrays;
import java.util.Random;

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

    private final double[] srcArray;
    private int size;
    private int halfSize;

    private static long start;
    private static long end;


    public ValueCalculator(int size) {
        this.size = Math.max(size, 1_000_000);
        this.halfSize = size / 2;
        this.srcArray = new double[this.size];
    }

    public void calculate() throws InterruptedException {

        start = System.currentTimeMillis();

        Arrays.fill(srcArray, 1.0);

        double[] firstPartArray = new double[halfSize];
        double[] secondPartArray = new double[halfSize];

        System.arraycopy(srcArray, 0, firstPartArray, 0, halfSize);
        System.arraycopy(srcArray, halfSize, secondPartArray, 0, halfSize);

        Thread thread1 = new Thread(() -> arrayChanger(firstPartArray));
        Thread thread2 = new Thread(() -> arrayChanger(secondPartArray));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.arraycopy(firstPartArray, 0, srcArray, 0, halfSize);

        System.arraycopy(secondPartArray, 0, srcArray, halfSize, halfSize);

        end = System.currentTimeMillis();
        System.out.println("Elapsed time with 2 threads: " + (end - start));

        // --------------------------------------------------------------

        start = System.currentTimeMillis();
        Arrays.fill(srcArray, 1.0);
        arrayChanger(srcArray);
        end = System.currentTimeMillis();
        System.out.println("Elapsed time with 1 thread: " + (end - start));

    }

    public void arrayChanger(double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    public void shuffleArray() {
        Random random = new Random();
        for (int i = srcArray.length - 1; i > 0; i--) {
            int randomIndex = random.nextInt(i + 1);
            double temp = srcArray[randomIndex];
            srcArray[randomIndex] = srcArray[i];
            srcArray[i] = temp;
        }
    }

    public void findMaxValue() {
        double maxValue = srcArray[0];
        start = System.currentTimeMillis();
        for (double d : srcArray) {
            if (d > maxValue) {
                maxValue = d;
            }
        }
        end = System.currentTimeMillis();
        System.out.println("Max value = " + maxValue + " Elapsed time with 1 thread: " + (end - start));
    }

    public void findMaxSeveralThreads(int numberOfThreads) throws InterruptedException {
        start = System.currentTimeMillis();
        ThreadsHandler[] threads = new ThreadsHandler[numberOfThreads];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new ThreadsHandler(i, numberOfThreads);
            threads[i].start();
        }
        for (ThreadsHandler t : threads) {
            t.join();
        }
        double max = Double.MIN_VALUE;
        for (ThreadsHandler t : threads) {
            if (t.getMaxValue() > max) {
                max = t.getMaxValue();
            }
        }
        end = System.currentTimeMillis();
        System.out.println("Max value = " + max + " Elapsed time with " + numberOfThreads + " thread: " + (end - start));
    }

    private class ThreadsHandler extends Thread {

        private double maxValue;

        private final int startIndex;

        private final int step;

        public ThreadsHandler(int startIndex, int step) {
            this.startIndex = startIndex;
            this.step = step;
        }

        public double getMaxValue() {
            return maxValue;
        }

        @Override
        public void run() {
            for (int i = startIndex; i < srcArray.length; i += step) {
                if (srcArray[i] > maxValue) {
                    maxValue = srcArray[i];
                }
            }
        }
    }

    public static void main(String[] args) {

        ValueCalculator valueCalculator = new ValueCalculator(1_000_000);

        try {
            valueCalculator.calculate();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        valueCalculator.shuffleArray();

        valueCalculator.findMaxValue();

        int[] threads = {2, 5, 10};
        for (int i : threads) {
            try {
                valueCalculator.findMaxSeveralThreads(i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}