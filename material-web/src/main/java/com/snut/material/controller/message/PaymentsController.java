package com.snut.material.controller.message;

import com.github.pagehelper.PageInfo;
import com.snut.material.model.PaymentsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.snut.material.common.CommonResult;
import com.snut.material.service.message.PaymentsService;

@RestController
@RequestMapping(path = "/admin/paymentsCtl")
public class PaymentsController {

    @Autowired
    PaymentsService paymentsService;

    /**
     * 获取当前所有信息列表
     * @param num
     * @param pageSize
     * @return
     */
    @GetMapping(path = "/payments/{num}/{pageSize}")
    public CommonResult adminList(@PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<PaymentsEntity> pageInfo = paymentsService.findPaymentsList(num, pageSize);

        commonResult = new CommonResult(200, pageInfo, "查询成功");

        return commonResult;
    }

    /**
     * 根据传来的account查询相应信息
     * @param account
     * @param num
     * @param pageSize
     * @return
     */
    @GetMapping(path = "/findPayments/{account}/{num}/{pageSize}")
    public CommonResult findPayments(@PathVariable("account") String account, @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<PaymentsEntity> pageInfo = paymentsService.findPayments(account, num, pageSize);

        commonResult = new CommonResult(200, pageInfo, "查询成功");
        return commonResult;
    }

    /**
     * 修改状态，一般用于是否封号，是否发布
     * @param row
     * @param flag
     * @param adminToken
     * @return
     */
    @GetMapping(path = "/state/{row}/{flag}")
    public CommonResult changeState(@PathVariable("row") Integer row, @PathVariable("flag") Integer flag, @RequestHeader("adminToken") String adminToken) {
        CommonResult commonResult = null;
        paymentsService.changeState(row, flag);
        commonResult = new CommonResult(200, null, "修改成功");
        return commonResult;
    }

    /**
     * 保存和新增信息
     * @param payments
     * @param adminToken
     * @return
     */
    @PostMapping(path = "/savePayments")
    public CommonResult savePayments(@RequestBody PaymentsEntity payments, @RequestHeader("adminToken") String adminToken) {
        CommonResult commonResult = null;
        if (payments.getId() == null) {
            paymentsService.savePayments(payments, adminToken);
        } else {
            paymentsService.update(payments, adminToken);
        }

        commonResult = new CommonResult(200, null, "保存成功");

        return commonResult;
    }

    /**
     * 通过id查找相应的信息，一般用于编辑时显示
     * @param id
     * @return
     */
    @GetMapping(path = "/findPaymentsByid/{id}")
    public CommonResult findPaymentsByid(@PathVariable("id") Integer id) {
        CommonResult commonResult = null;
        PaymentsEntity payments = paymentsService.findPaymentsByid(id);
        commonResult = new CommonResult(200, payments, "查询成功");

        return commonResult;
    }

    /**
     * 通过id进行删除
     * @param id
     * @param adminToken
     * @return
     */
    @GetMapping(path = "/delete/{id}")
    public CommonResult delete(@PathVariable("id") Integer id, @RequestHeader("adminToken") String adminToken) {
        CommonResult commonResult = null;
        paymentsService.delete(id);
        commonResult = new CommonResult(200, null, "删除成功");

        return commonResult;
    }


}
