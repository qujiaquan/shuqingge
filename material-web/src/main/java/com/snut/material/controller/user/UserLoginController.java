package com.snut.material.controller.user;

import com.snut.material.common.CommonResult;
import com.snut.material.dao.user.UserLoginDao;
import com.snut.material.model.UserEntity;
import com.snut.material.service.message.UserService;
import com.snut.material.service.user.UserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;


@RestController
@RequestMapping(path = "/user/loginCtl")
public class UserLoginController {

    static Logger logger = LoggerFactory.getLogger(UserLoginController.class);


    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    UserLoginService userLoginService;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    UserLoginDao loginDao;
    @Autowired
    UserService userService;

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @PostMapping(path = "/login")
    public CommonResult userLogin(@RequestBody UserEntity user) {

        CommonResult commonResult = null;
        UserEntity user1 = userLoginService.login(user);
        if (user1 != null) {
            if (user1.getFlag() == 0) {
                commonResult = new CommonResult(202, null, "该账号已封禁，请联系管理员");
            } else {
                commonResult = new CommonResult(200, user1, "登录成功");
            }
        } else {
            commonResult = new CommonResult(201, null, "账号或密码错误");
        }
        return commonResult;
    }


    /**
     * 创建邮箱登录的验证码
     *
     * @param account
     * @return
     */
    @GetMapping(path = "/createyzm/{account}")
    /*@PathVariable只针对get 传参，get传输的少量参数*/
    public CommonResult createyzm(@PathVariable("account") String account) {
        CommonResult commonResult = null;
        userLoginService.createyzm(account);
        commonResult = new CommonResult(200, null, "验证码发送成功");
        return commonResult;
    }

    /**
     * 用户邮箱账号注册
     *
     * @param user
     * @return
     */
    @PostMapping(path = "/register")
    public CommonResult register(@RequestBody UserEntity user, @RequestHeader("userToken") String userToken) {

        CommonResult commonResult = null;
        UserEntity user1 = userLoginService.ifAccount(user.getAccount());
        if (user1 != null) {
            commonResult = new CommonResult(201, user, "此账号已存在，请直接去登陆");
        } else {
            //保护用户信息
            userService.saveUserReg(user);
            commonResult = new CommonResult(200, null, "注册成功");
        }
        return commonResult;
    }

    /**
     * 用户忘记邮箱密码
     *
     * @param user
     * @return
     */
    @PostMapping(path = "/forget")
    public CommonResult forget(@RequestBody UserEntity user) {

        CommonResult commonResult = null;
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String value = (String) valueOperations.get(user.getAccount());//通过邮箱作为key，去获取值(验证码)
        UserEntity user1 = userLoginService.ifAccount(user.getAccount());
        if (user1 == null) {
            commonResult = new CommonResult(201, user, "此账号不存在");
        } else if (value == null) {
            commonResult = new CommonResult(201, null, "验证码失效");
        } else if (value.equals(user.getCode())) {
            //保护用户信息
            userLoginService.forget(user);
            commonResult = new CommonResult(200, null, "修改密码成功");
        } else {
            commonResult = new CommonResult(201, null, "验证码错误");
        }
        return commonResult;
    }

    @GetMapping(path = "/sendOffer/{account}")
    public void sendOffer(@PathVariable("account") String account) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = null;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            // 邮件发送来源
            mimeMessageHelper.setFrom("美团<3287033742@qq.com>");
            // 邮件发送目标
            mimeMessageHelper.setTo(account);
            // 设置标题
            mimeMessageHelper.setSubject("美团offer通知书");

            String str = "跨越山海，终于找到最优秀的你!恭喜你以出色的表现通过了所有面试，现在我们非常荣幸地邀请你加入美团外卖大家庭。多元业务，等你飞奔疾驰;广阔舞台，待你大展身手。未来，我们一起成长，一起更好!<br><br>" +
                    "以下是你的 offer 信息，请务必在PC端查看并进行操作哦!<br><br>" +
                    "入职即送电动车。<br><br>" +
                    "黄袍加身，餐餐大鱼大肉。<br><br>" +
                    "职位:JVAV开发工程师 + 外卖配送员";
            // 设置内容，并设置内容 html 格式为 true
            mimeMessageHelper.setText(str, true);
            javaMailSender.send(mimeMessage);


        } catch (Exception e) {
            e.printStackTrace();
        }
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("美团<3287033742@qq.com>");
//        message.setTo(account);
//        message.setSubject("美团offer通知书");
//
//        message.setText();
//        message.setSubject("美团offer通知书");//邮箱内容
//        mailSender.send(message);//发送邮箱
    }


}
