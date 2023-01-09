package kira.learn.cloud.user.listener;

import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/1/10 0:00
 */
@Component
public class MultiUserListener  {


    @EventListener(HttpSessionEvent.class)
    public void register(HttpSessionEvent userRegisterEvent) {
        HttpSession session = userRegisterEvent.getSession();
        System.out.println(session);
    }


}