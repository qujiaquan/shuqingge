package com.snut.material.service.user;

import com.snut.material.dao.user.UserLoginDao;
import com.snut.material.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import com.snut.material.common.JWTUtil;

import java.util.Random;
import java.util.concurrent.TimeUnit;


@Service
@Transactional
public class UserLoginService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    UserLoginDao loginDao;

    public UserEntity login(UserEntity user){

        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        UserEntity user1 = loginDao.userLogin(user);
        if (user1!=null){
            String token = JWTUtil.token(user1.getId(),user1.getAccount(),user1.getType(),user1.getName());
            user1.setToken(token);
        }
        return user1;
    }



    public void createyzm(String account) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("抒情阁<3287033742@qq.com>");
        message.setTo(account);
        message.setSubject("主题：邮箱验证码");

        //生成6位随机验证码
        Random random = new Random();
        int randomNum = random.nextInt(1000000);
        String randomCode = String.format("%06d", randomNum);//不够6位补0
        message.setText(randomCode);//邮箱内容
        mailSender.send(message);//发送邮箱

        //把验证码存入到redis
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(account, randomCode, 5, TimeUnit.MINUTES);

    }

    public UserEntity ifAccount(String account) {
        UserEntity user = loginDao.ifAccount(account);
        return user;
    }

    public void register(UserEntity user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        loginDao.register(user);
    }


    public void forget(UserEntity user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        loginDao.forget(user);
    }
}
