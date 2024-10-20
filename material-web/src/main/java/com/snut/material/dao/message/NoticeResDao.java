package com.snut.material.dao.message;


import org.springframework.stereotype.Repository;
import com.snut.material.model.NoticeResEntity;
import java.util.List;


@Repository
public interface NoticeResDao {


    void saveNoticeRes(NoticeResEntity noticeRes);

    NoticeResEntity findNoticeResByid(Integer id);

    void updateNoticeRes(NoticeResEntity noticeRes);

    void delete(Integer id);

    List<NoticeResEntity> findNoticeResList();

    void changeStata(Integer row, Integer flag);

    List<NoticeResEntity> findNoticeRes(String account);

    List<NoticeResEntity> findNoticeResByReqId(Integer id);
}
