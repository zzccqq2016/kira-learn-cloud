package kira.learn.cloud.user.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/1/30 16:21
 */
public class SsoUserDetails extends User {


    public SsoUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}