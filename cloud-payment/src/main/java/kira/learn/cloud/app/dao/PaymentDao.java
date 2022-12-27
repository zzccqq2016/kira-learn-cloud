package kira.learn.cloud.app.dao;

import kira.learn.cloud.common.bean.po.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Zhang Chaoqing
 * @date: 2022/12/22 16:52
 */
@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer> {
}