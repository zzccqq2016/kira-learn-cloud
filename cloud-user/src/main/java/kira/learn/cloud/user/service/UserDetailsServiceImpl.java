package kira.learn.cloud.user.service;

import kira.learn.cloud.user.bean.SsoUserDetails;
import kira.learn.cloud.user.dao.UserRepository;
import kira.learn.cloud.user.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/1/30 16:09
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + "用户不存在"));
        return toUserDetails(user);
    }


    private UserDetails toUserDetails(User user) {
        return new SsoUserDetails(user.getUsername(), user.getPassword(), getAuthorities(user));
    }


    private List<SimpleGrantedAuthority> getAuthorities(User user) {
        return new ArrayList<>();
    }


}