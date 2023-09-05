package com.sandystack.exp.services.concurrent;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ParallelCalculator {

    public void calculateMetrics(int items) throws InterruptedException {
        List<Integer> numbers = IntStream.rangeClosed(1, items).boxed().collect(Collectors.toList());

        final int threadsNumber = 3;
        int span = items / threadsNumber;

        List<CompletableFuture<List<Integer>>> futures = new ArrayList<>();

        // Dynamically creating futures based on threadsNumber
        for (int i = 0; i < threadsNumber; i++) {
            int start = i * span;
            int end = (i + 1) * span;
            futures.add(processAsync(numbers.subList(start, end)));
        }

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        CompletableFuture<List<Integer>> finalResult = combinedFuture.thenApply(v -> {
            List<Integer> combined = new ArrayList<>();
            for (CompletableFuture<List<Integer>> future : futures) {
                try {
                    combined.addAll(future.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            return combined;
        });

        try {
            System.out.println("Final combined results");
            finalResult.get().forEach(System.out::println);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static CompletableFuture<List<Integer>> processAsync(List<Integer> numbers) throws InterruptedException {

        System.out.println("started processing..." + Thread.currentThread().getName());
        Thread.sleep(new Random().nextInt(1000, 5000));
        System.out.println("finished processing..." + Thread.currentThread().getName());

        return CompletableFuture.supplyAsync(() -> numbers.stream()
                .map(n -> n * n) // Simulate a computation, like squaring the number
                .collect(Collectors.toList()));
    }
}
