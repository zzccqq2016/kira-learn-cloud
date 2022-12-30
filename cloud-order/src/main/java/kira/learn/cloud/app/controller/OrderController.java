package kira.learn.cloud.app.controller;

import kira.learn.cloud.app.feign.PaymentClient;
import kira.learn.cloud.common.bean.common.CommonResp;
import kira.learn.cloud.common.bean.po.Payment;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: Zhang Chaoqing
 * @date: 2022/12/26 13:25
 */
@RequestMapping("order")
@RestController
public class OrderController {

    @Resource
    private PaymentClient paymentClient;

    @PostMapping("create")
    private CommonResp<Payment> create(@RequestBody Payment payment) {
        return paymentClient.create(payment);
    }

    @GetMapping("get/{id}")
    public CommonResp<Payment> get(@PathVariable Integer id) {
        return paymentClient.get(id);
    }


    @GetMapping("get/timeout/{id}")
    public CommonResp<?> getTimeout(@PathVariable Integer id) {
        return paymentClient.getTimeout(id);
    }

}