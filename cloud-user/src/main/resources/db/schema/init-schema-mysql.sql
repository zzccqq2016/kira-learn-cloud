DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `id`        varchar(20)      NOT NULL COMMENT '用户id',
    `username`  varchar(50)      NULL     DEFAULT NULL COMMENT '用户名称',
    `password`  varchar(255)     NULL     DEFAULT NULL,
    `status`    int(10) UNSIGNED NOT NULL DEFAULT 0,
    `real_name` varchar(100)     NULL     DEFAULT NULL COMMENT '用户姓名',
    `email`     varchar(255)     NULL     DEFAULT NULL COMMENT '邮箱',
    `mobile`    varchar(20)      NULL     DEFAULT '' COMMENT '电话',
    `position`  varchar(100)     NULL     DEFAULT NULL COMMENT '职位',
    `company`   varchar(255)     NULL     DEFAULT NULL COMMENT '公司',
    `industry`  varchar(255)     NULL     DEFAULT NULL COMMENT '行业',
    `create_at` timestamp        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at` timestamp        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_mobile` (`mobile`) USING BTREE,
    UNIQUE INDEX `uk_username` (`username`) USING BTREE
) ENGINE = InnoDB COMMENT = '用户信息，包括申请试用信息'
  ROW_FORMAT = Dynamic;