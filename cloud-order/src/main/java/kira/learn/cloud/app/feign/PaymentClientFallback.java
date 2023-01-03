package kira.learn.cloud.app.feign;

import kira.learn.cloud.common.bean.common.CommonResp;
import kira.learn.cloud.common.bean.po.Payment;
import org.springframework.stereotype.Service;

/**
 * @author: Zhang Chaoqing
 * @date: 2022/12/29 16:18
 */
@Service
public class PaymentClientFallback implements PaymentClient {


    @Override
    public CommonResp<Payment> create(Payment payment) {
        return CommonResp.fail("feign集成sentinel 服务降级");
    }


    @Override
    public CommonResp<Payment> get(Integer id) {
        return CommonResp.fail("feign集成sentinel 服务降级");
    }

    @Override
    public CommonResp<Payment> getTimeout(Integer id) {
        return CommonResp.fail("feign集成sentinel 服务降级");
    }

    @Override
    public CommonResp<?> getResource(Integer id) {
        return CommonResp.fail("feign集成sentinel 服务降级");
    }


}