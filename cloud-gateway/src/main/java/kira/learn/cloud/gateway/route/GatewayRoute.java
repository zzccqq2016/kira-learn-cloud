//package kira.learn.cloud.gateway.route;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author: Zhang Chaoqing
// * @date: 2022/12/30 14:30
// */
//@Configuration(proxyBeanMethods = false)
//public class GatewayRoute {
//
//
//    /**
//     * 动态路由负载均衡固定写法 lb://serviceId {@link org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties#urlExpression}
//     * @param builder builder
//     * @return RouteLocator
//     */
//    @Bean
//    public RouteLocator orderRoute(RouteLocatorBuilder builder){
//        return builder.routes()
//                .route("order_route",r->r.path("/order/**").uri("lb://order")).build();
//    }
//
//
//    @Bean
//    public RouteLocator paymentRoute(RouteLocatorBuilder builder){
//        return builder.routes()
//                .route("payment_route",r->r.path("/payment/**").uri("lb://payment")).build();
//    }
//
//}