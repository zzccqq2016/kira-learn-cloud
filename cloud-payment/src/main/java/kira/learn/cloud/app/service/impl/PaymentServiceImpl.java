package kira.learn.cloud.app.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import kira.learn.cloud.app.dao.PaymentDao;
import kira.learn.cloud.app.service.PaymentService;
import kira.learn.cloud.common.bean.po.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author: Zhang Chaoqing
 * @date: 2022/12/21 21:20
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${server.port}")
    private Integer port;
    private final PaymentDao paymentDao;

    public PaymentServiceImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public Payment create(Payment payment) {
        return paymentDao.save(payment);
    }


    @Override
    public Payment getPaymentById(Integer id) {
        return paymentDao.findById(id).orElse(new Payment());
    }


    @HystrixCommand(fallbackMethod = "getPaymentByIdTimeOutHandler",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "6000")})
    @Override
    public Payment getPaymentByIdTimeout(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return paymentDao.findById(id).orElse(new Payment());
    }


    public Payment getPaymentByIdTimeOutHandler(Integer id){
        Payment payment = new Payment();
        payment.setId(port);
        payment.setName("timeOutHandler");
        return payment;
    }





}