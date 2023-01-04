package org.kira.web.demo;

import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/1/4 12:31
 */
public class SpringFactoriesDemo {

    public static void main(String[] args) {
        List<String> strings = SpringFactoriesLoader.loadFactoryNames(EnvironmentPostProcessor.class, EnvDemo.class.getClassLoader());
        for (String string : strings) {
            System.out.println(string);
        }
    }
}