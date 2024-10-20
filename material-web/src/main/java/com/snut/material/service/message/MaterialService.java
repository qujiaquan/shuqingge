package com.snut.material.service.message;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snut.material.dao.message.CategorieDao;
import com.snut.material.dao.message.FormatDao;
import com.snut.material.dao.message.UserDao;
import com.snut.material.model.CategorieEntity;
import com.snut.material.model.FormatEntity;
import com.snut.material.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.snut.material.common.JWTUtil;
import com.snut.material.dao.message.MaterialDao;
import com.snut.material.model.MaterialEntity;
import com.snut.material.unit.NowTime;

import java.util.List;


@Service
@Transactional
public class MaterialService {

    @Autowired
    MaterialDao materialDao;
    @Autowired
    UserDao userDao;
    @Autowired
    CategorieDao categorieDao;
    @Autowired
    FormatDao formatDao;


    public PageInfo<MaterialEntity> findMaterialList(Integer num, Integer pageSize) {

        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<MaterialEntity> materials = materialDao.findMaterialList();//查询列表时，会自动向sql中添加limit
        for (int i = 0; i < materials.size(); i++) {
            UserEntity user = userDao.findUserByid(materials.get(i).getUserId());
            materials.get(i).setUserName(user.getName());
            materials.get(i).setUserType(user.getType());
            CategorieEntity categorie = categorieDao.findCategorieByid(materials.get(i).getCategorieId());
            materials.get(i).setCategorieName(categorie.getName());
            FormatEntity format = formatDao.findFormatByid(materials.get(i).getFormatId());
            materials.get(i).setFormatName(format.getName());
        }
        PageInfo<MaterialEntity> pageInfo = new PageInfo<>(materials);//还会自动执行一个统计sql

        return pageInfo;
    }

    public void changeState(Integer row, Integer flag) {
        materialDao.changeStata(row, flag);
    }

    public PageInfo<MaterialEntity> findMaterial(String account, Integer num, Integer pageSize) {
        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<MaterialEntity> materials = materialDao.findMaterial(account);//查询列表时，会自动向sql中添加limit
        PageInfo<MaterialEntity> pageInfo = new PageInfo<>(materials);//还会自动执行一个统计sql
        return pageInfo;
    }


    public void saveMaterial(MaterialEntity material, String token) {
        //从token中获取当前操作人
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        String account = tokenInfo.getClaim("account").asString();
        Integer id = tokenInfo.getClaim("id").asInt();
        material.setUserId(id);
        material.setUploadTime(NowTime.getNowTime());
        material.setOperator(account);
        material.setOpertime(NowTime.getNowTime());
        materialDao.saveMaterial(material);

    }

    public MaterialEntity findMaterialByid(Integer id) {
        MaterialEntity materials = materialDao.findMaterialByid(id);
        if (materials == null) {
            return null;
        }
        UserEntity user = userDao.findUserByid(materials.getUserId());
        materials.setUserName(user.getName());
        materials.setUserType(user.getType());
        CategorieEntity categorie = categorieDao.findCategorieByid(materials.getCategorieId());
        materials.setCategorieName(categorie.getName());
        FormatEntity format = formatDao.findFormatByid(materials.getFormatId());
        materials.setFormatName(format.getName());
        return materials;
    }

    public void update(MaterialEntity material, String adminToken) {

        //从token中获取当前操作人
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(adminToken);
        String admin = tokenInfo.getClaim("account").asString();
        material.setOperator(admin);
        material.setOpertime(NowTime.getNowTime());
        //修改管理员表中的数据
        materialDao.updateMaterial(material);

    }

    public void delete(Integer id) {

        materialDao.delete(id);

    }

    /**
     * 通过素材分类查找素材信息
     * @param formatId
     * @param categorieId
     * @param num
     * @param pageSize
     * @return
     */
    public PageInfo<MaterialEntity> findMaterialByFormatIdAndCategorieId(String formatId, String categorieId, Integer num, Integer pageSize) {
        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<MaterialEntity> materials = materialDao.findMaterialByFormatIdAndCategorieId(formatId, categorieId);//查询列表时，会自动向sql中添加limit
        PageInfo<MaterialEntity> pageInfo = new PageInfo<>(materials);//还会自动执行一个统计sql
        return pageInfo;
    }

    /**
     * 获取最新的一条素材
     * @return
     */
    public MaterialEntity getNewFirstMaterial() {
        MaterialEntity materials = materialDao.getNewFirstMaterial();
        UserEntity user = userDao.findUserByid(materials.getUserId());
        materials.setUserName(user.getName());
        materials.setUserType(user.getType());
        CategorieEntity categorie = categorieDao.findCategorieByid(materials.getCategorieId());
        materials.setCategorieName(categorie.getName());
        FormatEntity format = formatDao.findFormatByid(materials.getFormatId());
        materials.setFormatName(format.getName());
        return materials;
    }

    public PageInfo<MaterialEntity> findMaterialsByFormatId(String formatId, Integer num, Integer pageSize) {
        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<MaterialEntity> materials = materialDao.findMaterialsByFormatId(formatId);//查询列表时，会自动向sql中添加limit
        PageInfo<MaterialEntity> pageInfo = new PageInfo<>(materials);//还会自动执行一个统计sql
        return pageInfo;
    }

    public PageInfo<MaterialEntity> findMaterialsByUserId(String userId, Integer num, Integer pageSize) {
        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<MaterialEntity> materials = materialDao.findMaterialsByUserId(userId);//查询列表时，会自动向sql中添加limit
        PageInfo<MaterialEntity> pageInfo = new PageInfo<>(materials);//还会自动执行一个统计sql
        return pageInfo;
    }

    public PageInfo<MaterialEntity> findNoticeReqByUserId(String userId, Integer num, Integer pageSize) {
        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<MaterialEntity> materials = materialDao.findNoticeReqByUserId(userId);//查询列表时，会自动向sql中添加limit
        PageInfo<MaterialEntity> pageInfo = new PageInfo<>(materials);//还会自动执行一个统计sql
        return pageInfo;
    }

    public PageInfo<MaterialEntity> findCollectionByUserId(String userId, Integer num, Integer pageSize) {
        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<MaterialEntity> materials = materialDao.findCollectionByUserId(userId);//查询列表时，会自动向sql中添加limit
        PageInfo<MaterialEntity> pageInfo = new PageInfo<>(materials);//还会自动执行一个统计sql
        return pageInfo;
    }

    public MaterialEntity findMaterialInfoByUserId(int id) {
        MaterialEntity materials = materialDao.findMaterialInfoByUserId(id);
        UserEntity user = userDao.findUserByid(materials.getUserId());
        materials.setUserName(user.getName());
        materials.setUserType(user.getType());
        CategorieEntity categorie = categorieDao.findCategorieByid(materials.getCategorieId());
        materials.setCategorieName(categorie.getName());
        FormatEntity format = formatDao.findFormatByid(materials.getFormatId());
        materials.setFormatName(format.getName());
        return materials;
    }

    public PageInfo<MaterialEntity> findMaterialByFormatIdAndIsFree(String formatId, String isFree, Integer num, Integer pageSize) {
        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<MaterialEntity> materials = materialDao.findMaterialByFormatIdAndIsFree(formatId, isFree);//查询列表时，会自动向sql中添加limit
        PageInfo<MaterialEntity> pageInfo = new PageInfo<>(materials);//还会自动执行一个统计sql
        return pageInfo;
    }
}
