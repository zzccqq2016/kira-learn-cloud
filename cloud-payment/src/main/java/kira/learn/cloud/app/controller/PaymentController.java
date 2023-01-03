package kira.learn.cloud.app.controller;

import kira.learn.cloud.app.service.PaymentService;
import kira.learn.cloud.common.bean.common.CommonResp;
import kira.learn.cloud.common.bean.po.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author: Zhang Chaoqing
 * @date: 2022/12/21 21:14
 */
@Slf4j
@RequestMapping("payment")
@RestController
public class PaymentController {

    @Value("${server.port}")
    private Integer port;

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("create")
    public CommonResp<Payment> create(@RequestBody Payment payment) {
        return CommonResp.success(paymentService.create(payment));
    }


    @GetMapping("get/{id}")
    public CommonResp<?> get(@PathVariable Integer id) {
        CommonResp<Payment> success = CommonResp.success(paymentService.getPaymentById(id));
        success.setCode(port);
        return success;
    }


    @GetMapping("get/timeout/{id}")
    public CommonResp<?> getTimeout(@PathVariable Integer id) {
        return CommonResp.success(paymentService.getPaymentByIdTimeout(id));
    }


    @GetMapping("get/resource/{id}")
    public CommonResp<?> getResource(@PathVariable Integer id) {
        if (id == 3) {
            try {
                TimeUnit.MILLISECONDS.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (id == 4) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (id > 4) {
            throw new RuntimeException("非法参数");
        }
        return paymentService.getResource();
    }


}