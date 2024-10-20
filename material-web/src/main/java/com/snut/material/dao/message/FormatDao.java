package com.snut.material.dao.message;


import org.springframework.stereotype.Repository;
import com.snut.material.model.FormatEntity;
import java.util.List;


@Repository
public interface FormatDao {


    void saveFormat(FormatEntity format);

    FormatEntity findFormatByid(Integer id);

    void updateFormat(FormatEntity format);

    void delete(Integer id);

    List<FormatEntity> findFormatList();

    void changeStata(Integer row, Integer flag);

    List<FormatEntity> findFormat(String account);

}
