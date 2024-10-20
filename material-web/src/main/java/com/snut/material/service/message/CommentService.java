package com.snut.material.service.message;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snut.material.dao.message.MaterialDao;
import com.snut.material.model.CollectionEntity;
import com.snut.material.model.MaterialEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.snut.material.common.JWTUtil;
import com.snut.material.dao.message.CommentDao;
import com.snut.material.model.CommentEntity;
import com.snut.material.unit.NowTime;
import java.util.List;


@Service
@Transactional
public class CommentService {

    @Autowired
    CommentDao commentDao;
    @Autowired
    MaterialDao materialDao;


    public PageInfo<CommentEntity> findCommentList(Integer num, Integer pageSize) {

        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<CommentEntity> comments = commentDao.findCommentList();//查询列表时，会自动向sql中添加limit
        PageInfo<CommentEntity> pageInfo = new PageInfo<>(comments);//还会自动执行一个统计sql

        return pageInfo;
    }

    public void changeState(Integer row, Integer flag) {
        commentDao.changeStata(row, flag);
    }

    public PageInfo<CommentEntity> findComment(String account,Integer num, Integer pageSize) {
        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<CommentEntity> comments = commentDao.findComment(account);//查询列表时，会自动向sql中添加limit
        PageInfo<CommentEntity> pageInfo = new PageInfo<>(comments);//还会自动执行一个统计sql
        return pageInfo;
    }


    public void saveComment(CommentEntity comment) {

        MaterialEntity material = materialDao.findMaterialByid(comment.getMaterialId());
        comment.setMaterialTitle(material.getTitle());
        comment.setTime(NowTime.getNowTime());
        commentDao.saveComment(comment);

    }

    public CommentEntity findCommentByid(Integer id) {
        CommentEntity comment = commentDao.findCommentByid(id);
        return comment;
    }

    public void update(CommentEntity comment, String adminToken) {

        //从token中获取当前操作人
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(adminToken);
        String admin = tokenInfo.getClaim("account").asString();
        comment.setTime(NowTime.getNowTime());
        //修改管理员表中的数据
        commentDao.updateComment(comment);

    }

    public void delete(Integer id) {

        commentDao.delete(id);

    }

    public PageInfo<CommentEntity> findCommentByUserId(String userId, Integer num, Integer pageSize) {
        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<CommentEntity> materials = commentDao.findCommentByUserId(userId);//查询列表时，会自动向sql中添加limit
        PageInfo<CommentEntity> pageInfo = new PageInfo<>(materials);//还会自动执行一个统计sql
        return pageInfo;
    }

    public List<CommentEntity> findMaterialComment(Integer userId, Integer materialId) {
        return commentDao.findMaterialComment(userId, materialId);
    }
}
