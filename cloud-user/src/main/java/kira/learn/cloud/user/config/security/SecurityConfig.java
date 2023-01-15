package kira.learn.cloud.user.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
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
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/custom/login").permitAll();
        http.authorizeRequests().antMatchers("/person/del/**").hasRole("administrators").antMatchers("/person/update").hasAnyRole("administrators", "auditor").antMatchers("/person/add").hasAnyRole("administrators", "operator").antMatchers("/person/findById/**", "/person/lists").access("permitAll");
        http.authorizeRequests().anyRequest().authenticated();
//        http.sessionManagement(session ->
//                //最大登录数1，拒绝第二个登录的请求
//                session.maximumSessions(1)
//                        .maxSessionsPreventsLogin(true));
        http.formLogin().
                //UsernamePasswordAuthenticationFilter path for login
                        loginProcessingUrl("/login");

        http.httpBasic();

        return http.build();
    }

    //    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

}