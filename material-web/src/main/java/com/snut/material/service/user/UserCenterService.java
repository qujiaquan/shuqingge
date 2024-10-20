package com.snut.material.service.user;

import com.snut.material.dao.user.UserCenterDao;
import com.snut.material.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCenterService {

    @Autowired
    UserCenterDao userCenterDao;
    public UserEntity getUserByid(Integer id) {
        return userCenterDao.getUserByid(id);
    }
}
