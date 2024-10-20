package com.snut.material.dao.message;


import org.springframework.stereotype.Repository;
import com.snut.material.model.NoticeReqEntity;
import java.util.List;


@Repository
public interface NoticeReqDao {


    void saveNoticeReq(NoticeReqEntity noticeReq);

    NoticeReqEntity findNoticeReqByid(Integer id);

    void updateNoticeReq(NoticeReqEntity noticeReq);

    void delete(Integer id);

    List<NoticeReqEntity> findNoticeReqList();

    void changeStata(Integer row, Integer flag);

    List<NoticeReqEntity> findNoticeReq(String account);

}
