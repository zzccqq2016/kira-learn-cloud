package kira.learn.cloud.app.feign;

/**
 * @author: Zhang Chaoqing
 * @date: 2022/12/27 12:47
 */
public interface PaymentServer {
    /**
     * Constant <code>NAME="${kg.kgms.path:kgms}"</code>
     */
    String NAME = "${payment.name:payment}";
    /**
     * Constant <code>URL="${kg.kgms.url:}"</code>
     */
    String URL = "${payment.url:}";
}
