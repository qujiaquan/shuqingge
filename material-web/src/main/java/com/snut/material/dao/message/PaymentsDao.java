package com.snut.material.dao.message;


import com.snut.material.model.MaterialEntity;
import org.springframework.stereotype.Repository;
import com.snut.material.model.PaymentsEntity;
import java.util.List;


@Repository
public interface PaymentsDao {


    void savePayments(PaymentsEntity payments);

    PaymentsEntity findPaymentsByid(Integer id);

    void updatePayments(PaymentsEntity payments);

    void delete(Integer id);

    List<PaymentsEntity> findPaymentsList();

    void changeStata(Integer row, Integer flag);

    List<PaymentsEntity> findPayments(String account);

    List<MaterialEntity> findPaymentByUserId(String userId);
}
