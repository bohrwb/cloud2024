DROP TABLE IF EXISTS `t_pay`;
CREATE TABLE `t_pay`  (
                          `id` int(10) UNSIGNED          NOT NULL AUTO_INCREMENT,
                          `pay_no` varchar(50)           NOT NULL COMMENT '支付流水号',
                          `order_no` varchar(50)         NOT NULL COMMENT '订单流水号',
                          `user_id` int(10)              NOT NULL DEFAULT 1 COMMENT '用户账号id',
                          `amount` decimal(8, 2)         NOT NULL COMMENT '交易金额',
                          `deleted` tinyint(4)  UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标志，默认0不删除，1删除',
                          `create_time` timestamp        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                          `update_time` timestamp        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT='支付交易表';

INSERT INTO t_pay(pay_no, order_no) VALUES('pay17203699','6544bafb424a');

SELECT * FROM t_pay;