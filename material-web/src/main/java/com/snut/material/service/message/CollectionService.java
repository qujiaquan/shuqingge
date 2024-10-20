package com.snut.material.service.message;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snut.material.dao.message.MaterialDao;
import com.snut.material.model.MaterialEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.snut.material.common.JWTUtil;
import com.snut.material.dao.message.CollectionDao;
import com.snut.material.model.CollectionEntity;
import com.snut.material.unit.NowTime;
import java.util.List;


@Service
@Transactional
public class CollectionService {

    @Autowired
    CollectionDao collectionDao;
    @Autowired
    MaterialDao materialDao;


    public PageInfo<CollectionEntity> findCollectionList(Integer num, Integer pageSize) {

        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<CollectionEntity> collections = collectionDao.findCollectionList();//查询列表时，会自动向sql中添加limit
        PageInfo<CollectionEntity> pageInfo = new PageInfo<>(collections);//还会自动执行一个统计sql

        return pageInfo;
    }

    public void changeState(Integer row, Integer flag) {
        collectionDao.changeStata(row, flag);
    }

    public PageInfo<CollectionEntity> findCollection(String account,Integer num, Integer pageSize) {
        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<CollectionEntity> collections = collectionDao.findCollection(account);//查询列表时，会自动向sql中添加limit
        PageInfo<CollectionEntity> pageInfo = new PageInfo<>(collections);//还会自动执行一个统计sql
        return pageInfo;
    }


    public void saveCollection(CollectionEntity collection) {
        MaterialEntity material = materialDao.findMaterialByid(collection.getMaterialId());
        collection.setImg(material.getImg());
        collection.setTime(NowTime.getNowTime());
        collectionDao.saveCollection(collection);

    }

    public CollectionEntity findCollectionByid(Integer id) {
        CollectionEntity collection = collectionDao.findCollectionByid(id);
        return collection;
    }

    public void update(CollectionEntity collection, String adminToken) {

        //从token中获取当前操作人
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(adminToken);
        String admin = tokenInfo.getClaim("account").asString();
        //修改管理员表中的数据
        collectionDao.updateCollection(collection);

    }

    public void delete(Integer id) {

        collectionDao.delete(id);

    }
    public void deleteCollection(Integer userId, Integer materialId) {
        collectionDao.deleteCollection(userId, materialId);
    }
    public CollectionEntity findUserIsCollection(Integer userId, Integer materialId) {
        return collectionDao.findUserIsCollection(userId, materialId);
    }


}
