package com.atguigu.cloud.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author wangbo
 * @date 2025/3/24
 */
@RestController
public class PayCircuitController {
    // Resilience4j CircuitBreaker演示
    @GetMapping("/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id) {
        if (-4 == id) {
            throw new RuntimeException("-----circuit id 不能负数");
        }
        if (9999 == id) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return "Hello, circuit! input id: " + id + IdUtil.simpleUUID();
    }
}
