package org.kira.reactive;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/1/4 15:22
 */
public class Demo {
    public static void main(String[] args) {
        List<Integer> elements  = new CopyOnWriteArrayList<>();
        Flux.just(1, 2, 3, 4)
                .log()
                .map(i -> i * 2)
                .subscribeOn(Schedulers.parallel())
                .subscribe(elements::add);


    }
}