package com.snut.material.dao.message;


import org.springframework.stereotype.Repository;
import com.snut.material.model.MaterialEntity;
import java.util.List;


@Repository
public interface MaterialDao {


    void saveMaterial(MaterialEntity material);

    MaterialEntity findMaterialByid(Integer id);

    void updateMaterial(MaterialEntity material);

    void delete(Integer id);

    List<MaterialEntity> findMaterialList();

    void changeStata(Integer row, Integer flag);

    List<MaterialEntity> findMaterial(String account);

    List<MaterialEntity> findMaterialByFormatIdAndCategorieId(String formatId, String categorieId);

    MaterialEntity getNewFirstMaterial();

    List<MaterialEntity> findMaterialsByFormatId(String formatId);

    List<MaterialEntity> findMaterialsByUserId(String userId);

    List<MaterialEntity> findNoticeReqByUserId(String userId);

    List<MaterialEntity> findCollectionByUserId(String userId);

    MaterialEntity findMaterialInfoByUserId(int id);

    List<MaterialEntity> findMaterialByFormatIdAndIsFree(String formatId, String isFree);

    MaterialEntity findMaterialByTitle(String title);
}
