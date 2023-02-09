package kira.learn.cloud.app.conf.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author: Zhang Chaoqing
 * Date: 2023/2/9 13:14
 */
@ConfigurationProperties("web.security")
public class WebSecurityProperties {

    /**
     * 不拦截的路径,不经过安全过滤器链
     */
    private List<String> excludeUrls;


    public List<String> getExcludeUrls() {
        return excludeUrls;
    }

    public void setExcludeUrls(List<String> excludeUrls) {
        this.excludeUrls = excludeUrls;
    }
}