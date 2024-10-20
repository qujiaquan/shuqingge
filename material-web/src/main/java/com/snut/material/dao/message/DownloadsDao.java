package com.snut.material.dao.message;


import com.snut.material.model.MaterialEntity;
import org.springframework.stereotype.Repository;
import com.snut.material.model.DownloadsEntity;
import java.util.List;


@Repository
public interface DownloadsDao {


    void saveDownloads(DownloadsEntity downloads);

    DownloadsEntity findDownloadsByid(Integer id);

    void updateDownloads(DownloadsEntity downloads);

    void delete(Integer id);

    List<DownloadsEntity> findDownloadsList();

    void changeStata(Integer row, Integer flag);

    List<DownloadsEntity> findDownloads(String account);

    List<MaterialEntity> findDownloadsByUserId(String userId);
}
