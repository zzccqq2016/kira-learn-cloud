package kira.learn.cloud.app.controller;

import kira.learn.cloud.app.service.PaymentService;
import kira.learn.cloud.common.bean.common.CommonResp;
import kira.learn.cloud.common.bean.po.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
        log.info("服务port:{}", port);
        return CommonResp.success(paymentService.getPaymentById(id));
    }
}