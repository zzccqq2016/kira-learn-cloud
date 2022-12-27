alter
database kira_cloud character set utf8mb4 collate utf8mb4_general_ci;

CREATE TABLE `t_payment`
(
    `id`   int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    `create_at`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;