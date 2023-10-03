package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleBlockingQueueTest {
    SimpleBlockingQueue<String> queue = new SimpleBlockingQueue<>(3);

    @Test
    void whenGet2() throws InterruptedException {
        queue.offer("1");
        queue.offer("2");
        queue.offer("3");
        Thread producer = new Thread(
                () -> {
                    try {
                        queue.offer("4");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        Thread consumer = new Thread(
                () -> {
                    try {
                        queue.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(queue.poll()).isEqualTo("2");
    }

    @Test
    void whenGet4() throws InterruptedException {
        queue.offer("1");
        queue.offer("2");
        queue.offer("3");
        Thread producer = new Thread(
                () -> {
                    try {
                        queue.offer("4");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        Thread consumer = new Thread(
                () -> {
                    try {
                        queue.poll();
                        queue.poll();
                        queue.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(queue.poll()).isEqualTo("4");
    }
}