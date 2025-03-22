package com.atguigu.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author wangbo
 * @date 2025/3/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayDTO {
    private Integer id;
    // 支付流水号
    private String payNo;
    // 订单流水号
    private String orderNo;
    // 用户账号id
    private Integer userId;
    // 交易金额
    private BigDecimal amount;
}
