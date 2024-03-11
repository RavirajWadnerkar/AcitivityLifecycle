package com.example.actiivitylifecycle;

public class CounterManager {
    private static int counter = 0;
    public static String lastActivityOpened = "";
    public static synchronized void increment() {
        counter++;
    }

    public static synchronized void add(int value) {
        counter += value;
    }

    public static synchronized int getCounter() {
        return counter;
    }

    public static synchronized void reset() {
        counter = 0;
    }
}
