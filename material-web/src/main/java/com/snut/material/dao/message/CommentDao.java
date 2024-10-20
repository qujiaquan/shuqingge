package com.snut.material.dao.message;


import com.snut.material.model.MaterialEntity;
import org.springframework.stereotype.Repository;
import com.snut.material.model.CommentEntity;
import java.util.List;


@Repository
public interface CommentDao {


    void saveComment(CommentEntity comment);

    CommentEntity findCommentByid(Integer id);

    void updateComment(CommentEntity comment);

    void delete(Integer id);

    List<CommentEntity> findCommentList();

    void changeStata(Integer row, Integer flag);

    List<CommentEntity> findComment(String account);

    List<CommentEntity> findCommentByUserId(String userId);

    List<CommentEntity> findMaterialComment(Integer userId, Integer materialId);
}
