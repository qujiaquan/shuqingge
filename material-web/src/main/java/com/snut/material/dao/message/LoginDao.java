package com.snut.material.dao.message;


import com.snut.material.model.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginDao {

    UserEntity login(UserEntity user);



}
