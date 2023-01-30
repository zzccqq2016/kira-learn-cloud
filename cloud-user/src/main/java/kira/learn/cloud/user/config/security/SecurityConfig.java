package kira.learn.cloud.user.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/1/9 22:21
 */
@Configuration(proxyBeanMethods = false)
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
//        http.sessionManagement(session ->
//                //最大登录数1，拒绝第二个登录的请求
//                session.maximumSessions(1)
//                        .maxSessionsPreventsLogin(true));
        http.formLogin().
                //UsernamePasswordAuthenticationFilter 登录接口
                        loginProcessingUrl("/login");

        http.csrf().disable();
        return http.build();
    }

    //    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/doc.html", "/swagger-resources/**", "/webjars/**", "/v2/**", "/api/**");
    }

}