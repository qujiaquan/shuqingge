package com.snut.material.service.message;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snut.material.model.MaterialEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.snut.material.common.JWTUtil;
import com.snut.material.dao.message.PaymentsDao;
import com.snut.material.model.PaymentsEntity;
import com.snut.material.unit.NowTime;
import java.util.List;


@Service
@Transactional
public class PaymentsService {

    @Autowired
    PaymentsDao paymentsDao;


    public PageInfo<PaymentsEntity> findPaymentsList(Integer num, Integer pageSize) {

        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<PaymentsEntity> paymentss = paymentsDao.findPaymentsList();//查询列表时，会自动向sql中添加limit
        PageInfo<PaymentsEntity> pageInfo = new PageInfo<>(paymentss);//还会自动执行一个统计sql

        return pageInfo;
    }

    public void changeState(Integer row, Integer flag) {
        paymentsDao.changeStata(row, flag);
    }

    public PageInfo<PaymentsEntity> findPayments(String account,Integer num, Integer pageSize) {
        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<PaymentsEntity> paymentss = paymentsDao.findPayments(account);//查询列表时，会自动向sql中添加limit
        PageInfo<PaymentsEntity> pageInfo = new PageInfo<>(paymentss);//还会自动执行一个统计sql
        return pageInfo;
    }


    public void savePayments(PaymentsEntity payments, String adminToken) {
        //从token中获取当前操作人
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(adminToken);
        String admin = tokenInfo.getClaim("account").asString();
        payments.setPaymentTime(NowTime.getNowTime());
        paymentsDao.savePayments(payments);

    }

    public PaymentsEntity findPaymentsByid(Integer id) {
        PaymentsEntity payments = paymentsDao.findPaymentsByid(id);
        return payments;
    }

    public void update(PaymentsEntity payments, String adminToken) {

        //从token中获取当前操作人
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(adminToken);
        String admin = tokenInfo.getClaim("account").asString();
        //修改管理员表中的数据
        paymentsDao.updatePayments(payments);

    }

    public void delete(Integer id) {

        paymentsDao.delete(id);

    }

    public PageInfo<MaterialEntity> findPaymentByUserId(String userId, Integer num, Integer pageSize) {
        PageHelper.startPage(num, pageSize);//告诉分页组件，当前页数，页数大小
        List<MaterialEntity> materials = paymentsDao.findPaymentByUserId(userId);//查询列表时，会自动向sql中添加limit
        PageInfo<MaterialEntity> pageInfo = new PageInfo<>(materials);//还会自动执行一个统计sql
        return pageInfo;
    }
}
