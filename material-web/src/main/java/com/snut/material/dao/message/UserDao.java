package com.snut.material.dao.message;


import org.springframework.stereotype.Repository;
import com.snut.material.model.UserEntity;
import java.util.List;


@Repository
public interface UserDao {


    void saveUser(UserEntity user);

    UserEntity findUserByid(Integer id);

    void updateUser(UserEntity user);

    void delete(Integer id);

    List<UserEntity> findUserList();

    void changeStata(Integer row, Integer flag);

    List<UserEntity> findUser(String account);

    void userPay(Integer userId, Integer num);
}
