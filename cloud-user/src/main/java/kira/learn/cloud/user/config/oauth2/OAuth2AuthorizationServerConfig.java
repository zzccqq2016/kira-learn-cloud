package kira.learn.cloud.user.config.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/2/1 16:30
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private PasswordEncoder passwordEncoder;


    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private ClientDetailsService clientDetailsServiceImpl;

    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    //http://localhost:8080/oauth/authorize?response_type=code&client_id=google-client-id&redirect_uri=http://127.0.0.1:8888/test&scope=all
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //授权码模式
//        //http://localhost:9999/oauth/authorize?response_type=code&client_id=client&redirect_uri=http://www.baidu.com&scope=all
//        // 简化模式
        clients.withClientDetails(clientDetailsServiceImpl);
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.tokenStore(new InMemoryTokenStore()).authenticationManager(authenticationManager).allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        endpoints.authenticationManager(authenticationManager).accessTokenConverter(jwtAccessTokenConverter);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        // 自定义异常处理端口
        security
                // oauth/token_key
                .tokenKeyAccess("permitAll()")
                // oauth/check_token
                .checkTokenAccess("isAuthenticated()")
//                .checkTokenAccess("permitAll()")
                // 允许客户表单认证
                .allowFormAuthenticationForClients();
    }

}