package com.snut.material.service.message;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.snut.material.common.JWTUtil;
import com.snut.material.dao.message.FormatDao;
import com.snut.material.model.FormatEntity;
import com.snut.material.unit.NowTime;
import java.util.List;


@Service
@Transactional
public class FormatService {

    @Autowired
    FormatDao formatDao;


    public PageInfo<FormatEntity> findFormatList(Integer num, Integer pageSize) {

        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<FormatEntity> formats = formatDao.findFormatList();//查询列表时，会自动向sql中添加limit
        PageInfo<FormatEntity> pageInfo = new PageInfo<>(formats);//还会自动执行一个统计sql

        return pageInfo;
    }

    public void changeState(Integer row, Integer flag) {
        formatDao.changeStata(row, flag);
    }

    public PageInfo<FormatEntity> findFormat(String account,Integer num, Integer pageSize) {
        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<FormatEntity> formats = formatDao.findFormat(account);//查询列表时，会自动向sql中添加limit
        PageInfo<FormatEntity> pageInfo = new PageInfo<>(formats);//还会自动执行一个统计sql
        return pageInfo;
    }


    public void saveFormat(FormatEntity format, String adminToken) {
        //从token中获取当前操作人
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(adminToken);
        String admin = tokenInfo.getClaim("account").asString();
        format.setOperator(admin);
        format.setOpertime(NowTime.getNowTime());
        formatDao.saveFormat(format);

    }

    public FormatEntity findFormatByid(Integer id) {
        FormatEntity format = formatDao.findFormatByid(id);
        return format;
    }

    public void update(FormatEntity format, String adminToken) {

        //从token中获取当前操作人
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(adminToken);
        String admin = tokenInfo.getClaim("account").asString();
        format.setOperator(admin);
        format.setOpertime(NowTime.getNowTime());
        //修改管理员表中的数据
        formatDao.updateFormat(format);

    }

    public void delete(Integer id) {

        formatDao.delete(id);

    }

    /**
     * 查询format所有的数据，一般用于选择
     * @return
     */
    public List<FormatEntity> findFormatAll() {
        return formatDao.findFormatList();
    }
}
