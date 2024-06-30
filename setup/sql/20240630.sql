CREATE TABLE `c2c_order` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主鍵',
  `order_id` varchar(32) DEFAULT NULL COMMENT '訂單編號',
  `user_id` int DEFAULT NULL COMMENT '用戶ID',
  `protocol` varchar(16) DEFAULT NULL COMMENT '區塊鏈的協議',
  `coin` varchar(16) DEFAULT NULL COMMENT '代幣代號',
  `amount` decimal(36,6) DEFAULT NULL COMMENT '代幣金額',
  `status` int DEFAULT NULL COMMENT '訂單狀態',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
  `update_date` datetime DEFAULT NULL COMMENT '更新時間',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_id_UNIQUE` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='c2c的訂單';
