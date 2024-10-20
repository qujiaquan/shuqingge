package com.snut.material.dao.message;


import org.springframework.stereotype.Repository;
import com.snut.material.model.CollectionEntity;
import java.util.List;


@Repository
public interface CollectionDao {


    void saveCollection(CollectionEntity collection);

    CollectionEntity findCollectionByid(Integer id);

    void updateCollection(CollectionEntity collection);

    void delete(Integer id);

    List<CollectionEntity> findCollectionList();

    void changeStata(Integer row, Integer flag);

    List<CollectionEntity> findCollection(String account);

    CollectionEntity findUserIsCollection(Integer userId, Integer materialId);

    void deleteCollection(Integer userId, Integer materialId);
}
