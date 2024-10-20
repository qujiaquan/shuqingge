package com.snut.material.service.message;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.snut.material.common.JWTUtil;
import com.snut.material.dao.message.AdminDao;
import com.snut.material.model.AdminEntity;
import com.snut.material.unit.NowTime;
import org.springframework.util.DigestUtils;

import java.util.List;


@Service
@Transactional
public class AdminService {

    @Autowired
    AdminDao adminDao;


    public PageInfo<AdminEntity> findAdminList(Integer num, Integer pageSize) {

        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<AdminEntity> admins = adminDao.findAdminList();//查询列表时，会自动向sql中添加limit
        PageInfo<AdminEntity> pageInfo = new PageInfo<>(admins);//还会自动执行一个统计sql

        return pageInfo;
    }

    public void changeState(Integer row, Integer flag) {
        adminDao.changeStata(row, flag);
    }

    public PageInfo<AdminEntity> findAdmin(String account,Integer num, Integer pageSize) {
        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<AdminEntity> admins = adminDao.findAdmin(account);//查询列表时，会自动向sql中添加limit
        PageInfo<AdminEntity> pageInfo = new PageInfo<>(admins);//还会自动执行一个统计sql
        return pageInfo;
    }


    public void saveAdmin(AdminEntity admin, String adminToken) {
        //从token中获取当前操作人
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(adminToken);
        String admin1 = tokenInfo.getClaim("account").asString();
        admin.setOperator(admin1);
        admin.setOpertime(NowTime.getNowTime());
        String password = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
        admin.setPassword(password);
        adminDao.saveAdmin(admin);
    }

    public AdminEntity findAdminByid(Integer id) {
        AdminEntity admin = adminDao.findAdminByid(id);
        return admin;
    }

    public void update(AdminEntity admin, String adminToken) {

        //从token中获取当前操作人
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(adminToken);
        String admin1 = tokenInfo.getClaim("account").asString();
        admin.setOperator(admin1);
        admin.setOpertime(NowTime.getNowTime());
        //修改管理员表中的数据
        adminDao.updateAdmin(admin);

    }

    public void delete(Integer id) {

        adminDao.delete(id);

    }

}
