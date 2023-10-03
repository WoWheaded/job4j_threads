package ru.job4j.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        var startAt = System.currentTimeMillis();
        var file = new File("tmp.xml");
        try (var in = new URL(url).openStream();
             var out = new FileOutputStream(file)) {
            System.out.println("Open connection: " + (System.currentTimeMillis() - startAt) + " ms");
            var dataBuffer = new byte[512];
            long countBytes = 0;
            int bytesRead;
            long time;
            while ((bytesRead = in.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                countBytes += countBytes;
                out.write(dataBuffer, 0, bytesRead);
                if (countBytes >= speed) {
                    time = (System.currentTimeMillis() - startAt);
                    if (time < 1000) {
                        try {
                            Thread.sleep(1000 - time);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    countBytes = 0;
                    startAt = System.currentTimeMillis();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void validateArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Arguments must be 2");
        }
        try {
            new URL(args[0]).toURI();
        } catch (URISyntaxException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        validateArgs(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
