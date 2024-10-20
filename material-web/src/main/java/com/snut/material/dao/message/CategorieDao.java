package com.snut.material.dao.message;


import com.snut.material.model.FormatEntity;
import org.springframework.stereotype.Repository;
import com.snut.material.model.CategorieEntity;
import java.util.List;


@Repository
public interface CategorieDao {


    void saveCategorie(CategorieEntity categorie);

    CategorieEntity findCategorieByid(Integer id);

    void updateCategorie(CategorieEntity categorie);

    void delete(Integer id);

    List<CategorieEntity> findCategorieList();

    void changeStata(Integer row, Integer flag);

    List<CategorieEntity> findCategorie(String account);

    List<FormatEntity> findCategorieByFormatId(Integer formatId);
}
