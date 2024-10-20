package com.snut.material.service.message;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.snut.material.common.JWTUtil;
import com.snut.material.dao.message.NoticeReqDao;
import com.snut.material.model.NoticeReqEntity;
import com.snut.material.unit.NowTime;
import java.util.List;


@Service
@Transactional
public class NoticeReqService {

    @Autowired
    NoticeReqDao noticeReqDao;


    public PageInfo<NoticeReqEntity> findNoticeReqList(Integer num, Integer pageSize) {

        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<NoticeReqEntity> noticeReqs = noticeReqDao.findNoticeReqList();//查询列表时，会自动向sql中添加limit
        PageInfo<NoticeReqEntity> pageInfo = new PageInfo<>(noticeReqs);//还会自动执行一个统计sql

        return pageInfo;
    }

    public void changeState(Integer row, Integer flag) {
        noticeReqDao.changeStata(row, flag);
    }

    public PageInfo<NoticeReqEntity> findNoticeReq(String account,Integer num, Integer pageSize) {
        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<NoticeReqEntity> noticeReqs = noticeReqDao.findNoticeReq(account);//查询列表时，会自动向sql中添加limit
        PageInfo<NoticeReqEntity> pageInfo = new PageInfo<>(noticeReqs);//还会自动执行一个统计sql
        return pageInfo;
    }


    public void saveNoticeReq(NoticeReqEntity noticeReq) {

        noticeReq.setReqTime(NowTime.getNowTime());
        noticeReqDao.saveNoticeReq(noticeReq);

    }

    public NoticeReqEntity findNoticeReqByid(Integer id) {
        NoticeReqEntity noticeReq = noticeReqDao.findNoticeReqByid(id);
        return noticeReq;
    }

    public void update(NoticeReqEntity noticeReq, String adminToken) {

        //从token中获取当前操作人
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(adminToken);
        String admin = tokenInfo.getClaim("account").asString();
        //修改管理员表中的数据
        noticeReqDao.updateNoticeReq(noticeReq);

    }

    public void delete(Integer id) {

        noticeReqDao.delete(id);

    }

}
