package com.snut.material.dao.user;


import com.snut.material.model.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginDao {



    UserEntity userLogin(UserEntity user);

    UserEntity ifAccount(String account);

    void register(UserEntity user);

    void forget(UserEntity user);
}
