package kira.learn.cloud.user.controller;

import kira.learn.cloud.common.bean.common.CommonResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/1/10 23:13
 */
@RestController
public class TestController {


    @GetMapping("get")
    public CommonResp<?> get() {
        return CommonResp.success();
    }

}