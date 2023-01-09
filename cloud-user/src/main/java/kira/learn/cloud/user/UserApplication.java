package kira.learn.cloud.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/1/9 22:18
 */
@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(UserApplication.class);
        springApplication.run(args);
    }
}