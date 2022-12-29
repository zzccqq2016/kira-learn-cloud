package kira.learn.cloud.app.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import kira.learn.cloud.app.feign.PaymentClient;
import kira.learn.cloud.common.bean.common.CommonResp;
import kira.learn.cloud.common.bean.po.Payment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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


    //    private static final String PAYMENT_URL = "http://127.0.0.1:8001";
    private static final String PAYMENT_URL = "http://payment";

    @Resource
    private RestTemplate restTemplate;


    @PostMapping("create")
    private CommonResp<Payment> create(@RequestBody Payment payment) {
//        CommonResp body = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResp.class).getBody();
        return paymentClient.create(payment);
    }

    @GetMapping("get/{id}")
    public CommonResp<Payment> get(@PathVariable Integer id) {
//        CommonResp resp = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResp.class).getBody();
        return paymentClient.get(id);
    }


    @GetMapping("get/timeout/{id}")
    public CommonResp<?> getTimeout(@PathVariable Integer id) {
        TimeInterval timer = DateUtil.timer();
        CommonResp<Payment> timeout = null;
        try {
            timeout = paymentClient.getTimeout(id);
        }catch (Exception e){
            //...
        }
        System.out.println(timer.interval());//花费毫秒数
        return timeout;
    }

}