package kira.learn.cloud.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author: Zhang Chaoqing
 * @date: 2022/12/21 20:42
 */
@SpringBootApplication
@EntityScan("kira.learn.cloud.common")
public class PaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }
}