package kira.learn.cloud.app.conf;

import kira.learn.cloud.app.conf.security.WebSecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Zhang Chaoqing
 * Date: 2023/2/9 11:43
 */
@EnableConfigurationProperties(WebSecurityProperties.class)
@Configuration(proxyBeanMethods = false)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and().oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

    private final WebSecurityProperties webSecurityProperties;

    public ResourceServerConfig(WebSecurityProperties webSecurityProperties) {
        this.webSecurityProperties = webSecurityProperties;
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        customIgnore(web);
    }

    private void customIgnore(WebSecurity web) {
        List<String> urls = new ArrayList<>();
        if (!CollectionUtils.isEmpty(webSecurityProperties.getExcludeUrls())) {
            urls.addAll(webSecurityProperties.getExcludeUrls());
        }
        web.ignoring().requestMatchers(new OrRequestMatcher(urls.stream().map(AntPathRequestMatcher::new).collect(Collectors.toList())));
    }
}