package com.snut.material.dao.user;

import com.snut.material.model.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCenterDao {

    UserEntity getUserByid(Integer id);
}
