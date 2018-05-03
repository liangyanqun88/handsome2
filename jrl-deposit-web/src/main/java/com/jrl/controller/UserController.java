package com.jrl.controller;

import com.jrl.model.Account.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangyanqun
 * @description
 * @date 2018-05-03 15:47
 */
@RestController
@RequestMapping(value = "/account")
@Api(tags = "账户类接口")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 创建个人账户
     */
    @ApiOperation(value = "开户",notes = "创建个人账户")
    @GetMapping(value = "/registerUser")
    public String registerUser(User user) {
        logger.info("name={}", user.getName());
        logger.info("注册成功");
        return "hello world";

    }

}
