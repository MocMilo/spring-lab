package com.sandystack.exp.jms;

import com.sandystack.exp.services.concurrent.ParallelCalculator;
import com.sandystack.exp.services.DummyDataProvider;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class MessageConsumer {

    private ParallelCalculator concurrentCalculator;
    private DummyDataProvider dummyDataProvider;


    @KafkaListener(topics = "test-topic2", groupId = "my-group")
    public void listen(String message) throws ExecutionException, InterruptedException {
        System.out.println("Kafka Consumer - received message: " + message);

        for (int i = 0; i < 2; i++) {
            concurrentCalculator.calculateMetrics(30);
        }
        dummyDataProvider.createDummyObjects();
    }
}
