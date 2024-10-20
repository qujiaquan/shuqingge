package com.snut.material.controller.message;

import com.snut.material.common.CommonResult;
import com.snut.material.model.UserEntity;
import com.snut.material.service.message.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin/loginCtl")
public class LoginController {

    static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login")
    public CommonResult login(@RequestBody UserEntity user) {

        CommonResult commonResult = null;
        UserEntity u = loginService.login(user);
        if (u != null) {
            commonResult = new CommonResult(200, u, "登录成功");
        } else {
            commonResult = new CommonResult(201, null, "账号或密码错误");
        }
        return commonResult;
    }
}
