package com.snut.material.dao.message;


import org.springframework.stereotype.Repository;
import com.snut.material.model.AdminEntity;
import java.util.List;


@Repository
public interface AdminDao {


    void saveAdmin(AdminEntity admin);

    AdminEntity findAdminByid(Integer id);

    void updateAdmin(AdminEntity admin);

    void delete(Integer id);

    List<AdminEntity> findAdminList();

    void changeStata(Integer row, Integer flag);

    List<AdminEntity> findAdmin(String account);

    String findOldPasswordByUserId(Integer id);

    void updatePassword(String newPassword, Integer id);
}
