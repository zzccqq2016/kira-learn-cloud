package kira.learn.cloud.user.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 监听用户登录触发的事件 {@link AbstractAuthenticationEvent}
 *
 * @author: Zhang Chaoqing
 * @date: 2023/1/10 23:27
 */
@Component
@Slf4j
public class AuthenticationSuccessListener implements ApplicationListener<AbstractAuthenticationEvent> {
    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent abstractAuthenticationEvent) {
        if (abstractAuthenticationEvent.getClass().isAssignableFrom(AuthenticationSuccessEvent.class)) {
            authenticationSuccessHandler(abstractAuthenticationEvent);
        } else if (abstractAuthenticationEvent.getClass().isAssignableFrom(AuthenticationFailureBadCredentialsEvent.class)) {//由于提供无效凭据而导致身份验证失败的应用程序事件。例如:用户名或密码错误
            authenticationFailureBadCredentialsHandler(abstractAuthenticationEvent);
        }
    }


    private void authenticationSuccessHandler(AbstractAuthenticationEvent abstractAuthenticationEvent) {
        if (abstractAuthenticationEvent.getClass().isAssignableFrom(AuthenticationSuccessEvent.class)) {
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) abstractAuthenticationEvent.getAuthentication();
            log.info("principal:{}", token.getPrincipal());
            log.info("credentials:{}", token.getCredentials());
            log.info("[{}] 登录成功", getUserName(token));
        }

    }


    /**
     * 由于提供无效凭据而导致身份验证失败的应用程序事件。例如:用户名或密码错误
     *
     * @param abstractAuthenticationEvent abstractAuthenticationEvent
     */
    private void authenticationFailureBadCredentialsHandler(AbstractAuthenticationEvent abstractAuthenticationEvent) {
        if (abstractAuthenticationEvent.getClass().isAssignableFrom(AuthenticationFailureBadCredentialsEvent.class)) {
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) abstractAuthenticationEvent.getAuthentication();
            log.info("principal:{}", token.getPrincipal());
            log.info("credentials:{}", token.getCredentials());
            log.info("[{}] 登录失败:无效凭据而导致身份验证失败", getUserName(token));
        }
    }


    private String getUserName(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal.getClass().isAssignableFrom(UserDetails.class)) {
            UserDetails userDetails = (UserDetails) principal;
            return userDetails.getUsername();
        } else {
            return String.valueOf(principal);
        }
    }


}