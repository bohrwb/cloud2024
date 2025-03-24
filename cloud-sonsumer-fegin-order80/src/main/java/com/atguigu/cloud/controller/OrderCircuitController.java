package com.atguigu.cloud.controller;

import com.atguigu.cloud.apis.PayFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author wangbo
 * @date 2025/3/24
 */
@RestController
public class OrderCircuitController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/feign/apy/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public String myCircuitBreaker(@PathVariable("id") Integer id) {
        return payFeignApi.myCircuit(id);
    }

    @GetMapping("/feign/apy/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback", type = Bulkhead.Type.SEMAPHORE)
    public String myBulkhead(@PathVariable("id") Integer id) {
        return payFeignApi.myCircuit(id);
    }

    @GetMapping("/feign/apy/bulkhead/threadPool/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkheadThreadPool", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> myBulkheadThreadPool(@PathVariable("id") Integer id) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return CompletableFuture.supplyAsync(() -> payFeignApi.myCircuit(id) + "\tBulkhead.Type.THREADPOOL");
    }

    public String myCircuitFallback(Throwable t) {
        return "myCircuitFallback, 系统繁忙,请稍后重试!";
    }

    public CompletableFuture<String> myBulkheadThreadPool(Integer id, Throwable t) {
        return CompletableFuture.supplyAsync(() -> "Bulkhead.Type.THREADPOOL, 系统繁忙,请稍后重试");
    }

    @GetMapping("/feign/apy/rateLimit/{id}")
    @RateLimiter(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public String myRateLimit(@PathVariable("id") Integer id) {
        return payFeignApi.myCircuit(id);
    }
}
