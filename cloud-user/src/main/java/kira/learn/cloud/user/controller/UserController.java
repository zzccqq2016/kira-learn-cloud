package kira.learn.cloud.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kira.learn.cloud.common.bean.common.CommonResp;
import kira.learn.cloud.user.bean.UserDto;
import kira.learn.cloud.user.dao.UserRepository;
import kira.learn.cloud.user.entity.User;
import kira.learn.cloud.user.util.StructConvert;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/1/30 17:13
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("user")
public class UserController {


    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserRepository userRepository;

    @Resource
    private StructConvert structConvert;

    @ApiOperation("注册用户")
    @PostMapping("/save")
    public CommonResp<?> save(@RequestBody @Validated UserDto dto) {
        User user = structConvert.toUser(dto);
        user.setId(user.getUsername());
        user.setStatus(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPosition("kira");
        return CommonResp.success(userRepository.save(user));
    }


}