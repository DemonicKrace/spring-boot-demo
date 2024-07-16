package com.example.user_login;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.Function;

/**
 * 併發測試用
 * <p>
 * 程式的進入點必定由 process => main thread 主執行緒進入點 => 基於main thread底下的子執行緒(sub thread),
 * Note: 子執行緒是依附於主執行緒之下, 主執行緒結束的話, 之下的子執行緒會直接結束
 */
@Slf4j
@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConcurrencyTest {

    @Test
    public void testConcurrencySubmitOrder() {
        // 預定執行的時間
        LocalDateTime targetDateTime = LocalDateTime.parse("2024-07-16T14:41:00");
        // 併發數
        int concurrencyCount = 3;
        try {
            RestTemplate restTemplate = new RestTemplate();
            for (int i = 0; i < concurrencyCount; i++) {
                // 異步 async 併發執行
                Thread thread = new Thread(() -> {
                    try {
                        Function<String, String> task = s -> {
                            // TODO: 併發測試的業務邏輯撰寫
                            return restTemplate.getForObject("http://127.0.0.1:8080/test/createBuyOrder", String.class);
                        };
                        concurrencyFunc(task, "", targetDateTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                thread.start();
            }
            // 等待時間(毫秒)
            int waitMills = 5000;
            // 主執行緒 等待 子執行緒執行完成
            Thread.sleep(Duration.between(LocalDateTime.now(), targetDateTime).toMillis() + waitMills);
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
