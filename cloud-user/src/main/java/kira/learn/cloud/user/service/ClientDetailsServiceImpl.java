package kira.learn.cloud.user.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/2/1 16:50
 */
@Component
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;


    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        if (!Objects.equals("client", clientId)) {
            return null;
        }
        BaseClientDetails baseClientDetails = new BaseClientDetails();
        //oauth2 clientId （账号）
        baseClientDetails.setClientId(clientId);
        //oauth2 secret (密码)  *需要加密*
        baseClientDetails.setClientSecret(passwordEncoder.encode("123"));
        //授权范围，也可根据这个范围标识，进行鉴权
        baseClientDetails.setScope(Collections.singletonList("all"));
        //注册回调地址
        baseClientDetails.setRegisteredRedirectUri(Collections.singleton("http://127.0.0.1:9527/gatewaylogin"));
        //授权类型: 授权码、刷新令牌、密码、客户端、简化模式、短信验证码 "refresh_token"
        baseClientDetails.setAuthorizedGrantTypes(Arrays.asList("authorization_code", "password", "client_credentials", "implicit", "sms_code"));
        //授权码模式 授权页面是否自动授权
        baseClientDetails.setAutoApproveScopes(Collections.singleton("true"));
        //拥有的权限
        baseClientDetails.setAuthorities(generateAuthority(Collections.singletonList("add:user")));
        //令牌有效时间
        baseClientDetails.setAccessTokenValiditySeconds(60 * 60);
        //刷新令牌有效时间
        baseClientDetails.setRefreshTokenValiditySeconds(60 * 60);
        return baseClientDetails;
    }


    private List<SimpleGrantedAuthority> generateAuthority(List<String> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;

    }

}