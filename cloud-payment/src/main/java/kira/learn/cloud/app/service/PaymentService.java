package kira.learn.cloud.app.service;


import kira.learn.cloud.common.bean.po.Payment;

/**
 * @author: Zhang Chaoqing
 * @date: 2022/12/21 21:19
 */
public interface PaymentService {
    Payment create(Payment payment);

    Payment getPaymentById(Integer id);
}
