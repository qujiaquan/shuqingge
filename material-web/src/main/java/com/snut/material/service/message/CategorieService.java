package com.snut.material.service.message;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snut.material.model.FormatEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.snut.material.common.JWTUtil;
import com.snut.material.dao.message.CategorieDao;
import com.snut.material.model.CategorieEntity;
import com.snut.material.unit.NowTime;
import java.util.List;


@Service
@Transactional
public class CategorieService {

    @Autowired
    CategorieDao categorieDao;


    public PageInfo<CategorieEntity> findCategorieList(Integer num, Integer pageSize) {

        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<CategorieEntity> categories = categorieDao.findCategorieList();//查询列表时，会自动向sql中添加limit
        PageInfo<CategorieEntity> pageInfo = new PageInfo<>(categories);//还会自动执行一个统计sql

        return pageInfo;
    }

    public void changeState(Integer row, Integer flag) {
        categorieDao.changeStata(row, flag);
    }

    public PageInfo<CategorieEntity> findCategorie(String account,Integer num, Integer pageSize) {
        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<CategorieEntity> categories = categorieDao.findCategorie(account);//查询列表时，会自动向sql中添加limit
        PageInfo<CategorieEntity> pageInfo = new PageInfo<>(categories);//还会自动执行一个统计sql
        return pageInfo;
    }


    public void saveCategorie(CategorieEntity categorie, String adminToken) {
        //从token中获取当前操作人
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(adminToken);
        String admin = tokenInfo.getClaim("account").asString();
        categorie.setOperator(admin);
        categorie.setOpertime(NowTime.getNowTime());
        categorieDao.saveCategorie(categorie);

    }

    public CategorieEntity findCategorieByid(Integer id) {
        CategorieEntity categorie = categorieDao.findCategorieByid(id);
        return categorie;
    }

    public void update(CategorieEntity categorie, String adminToken) {

        //从token中获取当前操作人
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(adminToken);
        String admin = tokenInfo.getClaim("account").asString();
        categorie.setOperator(admin);
        categorie.setOpertime(NowTime.getNowTime());
        //修改管理员表中的数据
        categorieDao.updateCategorie(categorie);

    }

    public void delete(Integer id) {

        categorieDao.delete(id);

    }

    public List<FormatEntity> findCategorieByFormatId(Integer formatId) {
        return categorieDao.findCategorieByFormatId(formatId);
    }
}
