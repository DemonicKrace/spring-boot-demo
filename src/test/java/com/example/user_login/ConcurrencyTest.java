package com.example.user_login;


import com.example.business.TestBusiness;
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
 *
 * 程式的進入點必定由 process => main thread 主執行緒進入點 => 基於main thread底下的子執行緒(sub thread),
 * Note: 子執行緒是依附於主執行緒之下, 主執行緒結束的話, 之下的子執行緒會直接結束
 */
@Slf4j
@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConcurrencyTest {

    private final TestBusiness testBusiness;

    @Test
    public void testConcurrencySubmitOrder() {
//        LocalDateTime targetDateTime = LocalDateTime.parse("2024-07-14T21:46:00");
        int concurrencyCount = 3;
        String orderId = "orderId";
        try {
            for (int i = 0; i < concurrencyCount; i++) {
                // 異步 async 併發執行
                Thread thread = new Thread(() -> {
                    try {
                        concurrencyCreateBuyOrder();
//                        Function<String, String> task = s -> {
//                            // TODO:
//                            return "";
//                        };
//                        concurrencyFunc(task, orderId, targetDateTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                thread.start();
            }
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 併發的提交 買單 去撮合
     */
    private void concurrencyCreateBuyOrder() {
        String logPrefix = "concurrencyCreateBuyOrder";
//        waitUntil(targetDateTime);
        LocalDateTime startTime = LocalDateTime.now();

        // TODO: 執行要併發的 業務邏輯處理

        // 需要另外去mock redis, 不然無法在測試模式下連上Redis
//        testBusiness.createBuyOrder();

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://127.0.0.1:8080/test/createBuyOrder", String.class);

        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, endTime);
        long executeMills = duration.toMillis();
        log.info("{}, startTime = {}, executeMills = {}, response = {}", logPrefix, startTime, executeMills, response);
    }

//    private void concurrencyFunc(Function task, Object param, LocalDateTime targetDateTime) {
//        String logPrefix = String.format("param = %s, targetDateTime = %s", param.toString(), targetDateTime);
//        waitUntil(targetDateTime);
//
//        if (Thread.currentThread().isInterrupted()) {
//            log.info("{}, thread isInterrupted = true", logPrefix);
//            return;
//        }
//        LocalDateTime startTime = LocalDateTime.now();
//        Object response = task.apply(param);
//        LocalDateTime endTime = LocalDateTime.now();
//        Duration duration = Duration.between(startTime, endTime);
//        long executeMills = duration.toMillis();
//        log.info("{}, startTime = {}, executeMills = {}, response = {}", logPrefix, startTime, executeMills, response.toString());
//    }

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
