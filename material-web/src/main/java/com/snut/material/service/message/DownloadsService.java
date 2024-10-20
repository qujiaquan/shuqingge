package com.snut.material.service.message;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snut.material.model.MaterialEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.snut.material.common.JWTUtil;
import com.snut.material.dao.message.DownloadsDao;
import com.snut.material.model.DownloadsEntity;
import com.snut.material.unit.NowTime;
import java.util.List;


@Service
@Transactional
public class DownloadsService {

    @Autowired
    DownloadsDao downloadsDao;


    public PageInfo<DownloadsEntity> findDownloadsList(Integer num, Integer pageSize) {

        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<DownloadsEntity> downloadss = downloadsDao.findDownloadsList();//查询列表时，会自动向sql中添加limit
        PageInfo<DownloadsEntity> pageInfo = new PageInfo<>(downloadss);//还会自动执行一个统计sql

        return pageInfo;
    }

    public void changeState(Integer row, Integer flag) {
        downloadsDao.changeStata(row, flag);
    }

    public PageInfo<DownloadsEntity> findDownloads(String account,Integer num, Integer pageSize) {
        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<DownloadsEntity> downloadss = downloadsDao.findDownloads(account);//查询列表时，会自动向sql中添加limit
        PageInfo<DownloadsEntity> pageInfo = new PageInfo<>(downloadss);//还会自动执行一个统计sql
        return pageInfo;
    }


    public void saveDownloads(DownloadsEntity downloads, String adminToken) {
        //从token中获取当前操作人
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(adminToken);
        String admin = tokenInfo.getClaim("account").asString();
        downloads.setDownloadTime(NowTime.getNowTime());
        downloadsDao.saveDownloads(downloads);

    }

    public DownloadsEntity findDownloadsByid(Integer id) {
        DownloadsEntity downloads = downloadsDao.findDownloadsByid(id);
        return downloads;
    }

    public void update(DownloadsEntity downloads, String adminToken) {

        //从token中获取当前操作人
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(adminToken);
        String admin = tokenInfo.getClaim("account").asString();
        //修改管理员表中的数据
        downloadsDao.updateDownloads(downloads);

    }

    public void delete(Integer id) {

        downloadsDao.delete(id);

    }

    public PageInfo<MaterialEntity> findDownloadsByUserId(String userId, Integer num, Integer pageSize) {
        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<MaterialEntity> materials = downloadsDao.findDownloadsByUserId(userId);//查询列表时，会自动向sql中添加limit
        PageInfo<MaterialEntity> pageInfo = new PageInfo<>(materials);//还会自动执行一个统计sql
        return pageInfo;
    }
}
