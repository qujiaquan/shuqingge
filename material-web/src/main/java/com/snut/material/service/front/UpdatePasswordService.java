package com.snut.material.service.front;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snut.material.common.JWTUtil;
import com.snut.material.dao.message.AdminDao;
import com.snut.material.model.AdminEntity;
import com.snut.material.model.PasswordEntity;
import com.snut.material.unit.NowTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;


@Service
@Transactional
public class UpdatePasswordService {

    @Autowired
    AdminDao adminDao;


    public boolean oldPassword(PasswordEntity password) {
        String oldpassword = adminDao.findOldPasswordByUserId(password.getId());
        String oldpassword1 = DigestUtils.md5DigestAsHex(password.getOldPassword().getBytes());
        if (oldpassword.equals(oldpassword1)) {
            return true;
        }
        return false;
    }

    public void updatePassword(String newPassword1, Integer id) {
        String newPassword = DigestUtils.md5DigestAsHex(newPassword1.getBytes());
        adminDao.updatePassword(newPassword, id);
    }
}
