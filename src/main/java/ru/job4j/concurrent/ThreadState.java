package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                });
        System.out.println("first thread name - " + first.getName());
        Thread second = new Thread(
                () -> {
                });
        System.out.println("second thread name - " + second.getName());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED && second.getState() != Thread.State.TERMINATED) {
            System.out.println("first thread state - " + first.getState());
            System.out.println("second thread state - " + second.getState());
        }
        System.out.println("first thread state END - " + first.getState());
        System.out.println("second thread state  END- " + second.getState());
        System.out.println("Завершение работы");
    }
}