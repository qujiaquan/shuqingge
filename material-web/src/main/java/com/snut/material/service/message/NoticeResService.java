package com.snut.material.service.message;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snut.material.dao.message.MaterialDao;
import com.snut.material.model.MaterialEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.snut.material.common.JWTUtil;
import com.snut.material.dao.message.NoticeResDao;
import com.snut.material.model.NoticeResEntity;
import com.snut.material.unit.NowTime;
import java.util.List;


@Service
@Transactional
public class NoticeResService {
    private static Logger logger = LoggerFactory.getLogger(NoticeResService.class);

    @Autowired
    NoticeResDao noticeResDao;
    @Autowired
    MaterialDao materialDao;


    public PageInfo<NoticeResEntity> findNoticeResList(Integer num, Integer pageSize) {

        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<NoticeResEntity> noticeRess = noticeResDao.findNoticeResList();//查询列表时，会自动向sql中添加limit
        PageInfo<NoticeResEntity> pageInfo = new PageInfo<>(noticeRess);//还会自动执行一个统计sql

        return pageInfo;
    }

    public void changeState(Integer row, Integer flag) {
        noticeResDao.changeStata(row, flag);
    }

    public PageInfo<NoticeResEntity> findNoticeRes(String account,Integer num, Integer pageSize) {
        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<NoticeResEntity> noticeRess = noticeResDao.findNoticeRes(account);//查询列表时，会自动向sql中添加limit
        PageInfo<NoticeResEntity> pageInfo = new PageInfo<>(noticeRess);//还会自动执行一个统计sql
        return pageInfo;
    }


    public void saveNoticeRes(MaterialEntity material) {

        materialDao.saveMaterial(material);
        MaterialEntity m = materialDao.findMaterialByTitle(material.getTitle());
        NoticeResEntity noticeRes = new NoticeResEntity();
        noticeRes.setTitle(material.getTitle());
        noticeRes.setDescription(material.getDescription());
        noticeRes.setNoticeReqId(material.getNoticeReqId());
        noticeRes.setUserId(material.getUserId());
        noticeRes.setMaterialId(m.getId());
        noticeRes.setResTime(NowTime.getNowTime());
        logger.info("需要新增的noticeRes信息：{}", noticeRes);
        noticeResDao.saveNoticeRes(noticeRes);
    }

    public NoticeResEntity findNoticeResByid(Integer id) {
        NoticeResEntity noticeRes = noticeResDao.findNoticeResByid(id);
        return noticeRes;
    }

    public void update(NoticeResEntity noticeRes, String adminToken) {

        //从token中获取当前操作人
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(adminToken);
        String admin = tokenInfo.getClaim("account").asString();
        noticeRes.setOperator(admin);
        noticeRes.setOpertime(NowTime.getNowTime());
        //修改管理员表中的数据
        noticeResDao.updateNoticeRes(noticeRes);

    }

    public void delete(Integer id) {

        noticeResDao.delete(id);

    }


    public List<NoticeResEntity> findNoticeResByReqId(Integer id) {
        return noticeResDao.findNoticeResByReqId(id);
    }
}
