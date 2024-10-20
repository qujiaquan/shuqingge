package com.snut.material.controller.front;

import com.snut.material.common.CommonResult;
import com.snut.material.model.PasswordEntity;
import com.snut.material.service.front.UpdatePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 修改密码类
 */
@RestController
@RequestMapping(path = "/front/updatePasswordCtl")
public class UpdatePasswordController {
    @Autowired
    UpdatePasswordService updatePasswordService;

    /**
     * 修改密码
     *
     * @param password PasswordEntity对象，里面有新密码和旧密码，先对旧密码进行判断，如果没有问题就修改为新密码
     * @param
     * @return
     */
    @PostMapping(path = "/updatePassword")
    public CommonResult updatePassword(@RequestBody PasswordEntity password) {
        CommonResult commonResult = null;
        boolean flag = updatePasswordService.oldPassword(password);
        if (flag) {
            updatePasswordService.updatePassword(password.getNewPassword1(), password.getId());
            commonResult = new CommonResult(200, null, "修改成功");
        } else {
            commonResult = new CommonResult(201, null, "原密码错误");
        }
        return commonResult;
    }
}
