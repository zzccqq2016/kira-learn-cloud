package kira.learn.cloud.app.feign;

import kira.learn.cloud.app.PaymentServer;
import kira.learn.cloud.common.bean.common.CommonResp;
import kira.learn.cloud.common.bean.po.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: Zhang Chaoqing
 * @date: 2022/12/27 12:31
 */
@FeignClient(value = PaymentServer.NAME, path = "payment", url = PaymentServer.URL)
public interface PaymentClient {


    @PostMapping("create")
    CommonResp<Payment> create(@RequestBody Payment payment);

//    @HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "6000")})
    @GetMapping("get/{id}")
    CommonResp<Payment> get(@PathVariable("id") Integer id);

    @GetMapping("get/timeout/{id}")
    CommonResp<Payment> getTimeout(@PathVariable("id") Integer id);

}