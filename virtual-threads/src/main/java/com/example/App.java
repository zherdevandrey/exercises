package com.example;


import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class App
{
    public static void main( String[] args )
    {
        try(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.rangeClosed(0, 100_000).forEach(i -> {
                executor.submit(() -> {
                    sleep();
                    System.out.println("worker " + i + " finished");
                    return i;
                });
            });
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(Duration.ofSeconds(1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
