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

    public void calculateMetrics() throws InterruptedException {

        List<Integer> numbers = IntStream.rangeClosed(1, 30).boxed().collect(Collectors.toList());

        CompletableFuture<List<Integer>> future1 = processAsync(numbers.subList(0, 10));
        CompletableFuture<List<Integer>> future2 = processAsync(numbers.subList(10, 20));
        CompletableFuture<List<Integer>> future3 = processAsync(numbers.subList(20, 30));

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);

        CompletableFuture<List<Integer>> finalResult = combinedFuture.thenApply(v -> {
            List<Integer> combined = new ArrayList<>();
            try {
                combined.addAll(future1.get());
                combined.addAll(future2.get());
                combined.addAll(future3.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
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
