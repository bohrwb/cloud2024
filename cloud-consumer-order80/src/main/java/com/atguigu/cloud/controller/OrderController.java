package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author wangbo
 * @date 2025/3/22
 */
@RestController
public class OrderController {
//    public static final String PAYMENT_SERVER_URL = "http://localhost:8001";
    public static final String PAYMENT_SERVER_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/consumer/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        return restTemplate.postForObject(PAYMENT_SERVER_URL + "/pay/add", payDTO, ResultData.class);
    }

    @DeleteMapping("/consumer/pay/del/{id}")
    public ResultData deleteOrder(@PathVariable("id") Integer id) {
        restTemplate.delete(PAYMENT_SERVER_URL + "/pay/del/" + id);
        return ResultData.success("success");
    }

    @PutMapping("/consumer/pay/update")
    public ResultData updateOrder(@RequestBody PayDTO payDTO) {
        restTemplate.put(PAYMENT_SERVER_URL + "/pay/update", payDTO);
        return ResultData.success("success");
    }

    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PAYMENT_SERVER_URL + "/pay/get/" + id, ResultData.class, id);
    }

    @GetMapping("/consumer/pay/get/info")
    public String getInfoByConsul() {
        return restTemplate.getForObject(PAYMENT_SERVER_URL + "/pay/get/info", String.class);
    }

}
