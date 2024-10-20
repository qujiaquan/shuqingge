package com.snut.material.service.message;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.snut.material.common.JWTUtil;
import com.snut.material.dao.message.UserDao;
import com.snut.material.model.UserEntity;
import com.snut.material.unit.NowTime;
import org.springframework.util.DigestUtils;

import java.util.List;


@Service
@Transactional
public class UserService {

    @Autowired
    UserDao userDao;


    public PageInfo<UserEntity> findUserList(Integer num, Integer pageSize) {

        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<UserEntity> users = userDao.findUserList();//查询列表时，会自动向sql中添加limit
        PageInfo<UserEntity> pageInfo = new PageInfo<>(users);//还会自动执行一个统计sql

        return pageInfo;
    }

    public void changeState(Integer row, Integer flag) {
        userDao.changeStata(row, flag);
    }

    public PageInfo<UserEntity> findUser(String account,Integer num, Integer pageSize) {
        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<UserEntity> users = userDao.findUser(account);//查询列表时，会自动向sql中添加limit
        PageInfo<UserEntity> pageInfo = new PageInfo<>(users);//还会自动执行一个统计sql
        return pageInfo;
    }


    public void saveUser(UserEntity user, String token) {
        //从token中获取当前操作人
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        String admin = tokenInfo.getClaim("account").asString();
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(password);
        user.setOperator(admin);
        user.setOpertime(NowTime.getNowTime());
        userDao.saveUser(user);

    }
    public void saveUserReg(UserEntity user) {
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(password);
        user.setOperator(user.getAccount());
        user.setOpertime(NowTime.getNowTime());
        userDao.saveUser(user);

    }

    public UserEntity findUserByid(Integer id) {
        UserEntity user = userDao.findUserByid(id);
        return user;
    }

    public void update(UserEntity user, String token) {

        //从token中获取当前操作人
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        String admin = tokenInfo.getClaim("account").asString();
        user.setOperator(admin);
        user.setOpertime(NowTime.getNowTime());
        //修改管理员表中的数据
        userDao.updateUser(user);

    }

    public void delete(Integer id) {

        userDao.delete(id);

    }


    public void userPay(Integer userId, Integer num) {
        userDao.userPay(userId, num);
    }
}
