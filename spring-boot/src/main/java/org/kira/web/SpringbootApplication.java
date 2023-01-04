package org.kira.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/1/4 11:29
 */
@SpringBootApplication
public class SpringbootApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringbootApplication.class);
        application.run(args);
    }






}