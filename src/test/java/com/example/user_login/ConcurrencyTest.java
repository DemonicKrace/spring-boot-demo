package com.example.user_login;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.Function;

@Slf4j
@SpringBootTest
public class ConcurrencyTest {

    @Test
    public void testConcurrencySubmitOrder() {
        LocalDateTime targetDateTime = LocalDateTime.parse("2024-07-14T10:55:15");
        int concurrencyCount = 20;
        String orderId = "orderId";
        try {
            for (int i = 0; i < concurrencyCount; i++) {
                Thread thread = new Thread(() -> {
                    try {
                        Function<String, String> task = s -> {
                            // TODO:
                            return "";
                        };
                        concurrencyFunc(task, orderId, targetDateTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void concurrencyFunc(Function task, Object param, LocalDateTime targetDateTime) {
        String logPrefix = String.format("param = %s, targetDateTime = %s", param.toString(), targetDateTime);
        waitUntil(targetDateTime);

        if (Thread.currentThread().isInterrupted()) {
            log.info("{}, thread isInterrupted = true", logPrefix);
            return;
        }
        LocalDateTime startTime = LocalDateTime.now();
        Object response = task.apply(param);
        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, endTime);
        long executeMills = duration.toMillis();
        log.info("{}, startTime = {}, executeMills = {}, response = {}", logPrefix, startTime, executeMills, response.toString());
    }

    private void waitUntil(LocalDateTime targetDateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, targetDateTime);
        long millis = duration.toMillis();
        if (millis > 0) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
