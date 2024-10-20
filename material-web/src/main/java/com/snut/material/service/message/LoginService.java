package com.snut.material.service.message;

import com.snut.material.common.JWTUtil;
import com.snut.material.dao.message.LoginDao;
import com.snut.material.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;



@Service
@Transactional
public class LoginService {

    @Autowired
    LoginDao loginDao;

    /**
     * 管理员登录，密码使用md5进行加密进行查找，如果账号密码正确就会创建token进行返回
     * @param user
     * @return
     */
    public UserEntity login(UserEntity user){
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
//        DigestUtils.md5DigestAsHex("111".getBytes()) //密码加密
        UserEntity u = loginDao.login(user);
        if (u!=null){
            String token = JWTUtil.token(u.getId(),u.getAccount(),u.getType(), u.getName());
            u.setToken(token);
        }
        return u;
    }

}
