package kira.learn.cloud.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/1/9 23:00
 */
@Controller
public class LoginController {


    @RequestMapping("custom/login")
    public String login() {
        return "redirect:/login";
    }

}