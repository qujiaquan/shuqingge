package com.snut.material.controller.message;

import com.github.pagehelper.PageInfo;
import com.snut.material.model.AdminEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.snut.material.common.CommonResult;
import com.snut.material.service.message.AdminService;

@RestController
@RequestMapping(path = "/admin/adminCtl")
public class AdminController {

    @Autowired
    AdminService adminService;

    /**
     * 获取当前所有信息列表
     * @param num
     * @param pageSize
     * @return
     */
    @GetMapping(path = "/admin/{num}/{pageSize}")
    public CommonResult adminList(@PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<AdminEntity> pageInfo = adminService.findAdminList(num, pageSize);

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
    @GetMapping(path = "/findAdmin/{account}/{num}/{pageSize}")
    public CommonResult findAdmin(@PathVariable("account") String account, @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<AdminEntity> pageInfo = adminService.findAdmin(account, num, pageSize);

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
        adminService.changeState(row, flag);
        commonResult = new CommonResult(200, null, "修改成功");
        return commonResult;
    }

    /**
     * 保存和新增信息
     * @param admin
     * @param adminToken
     * @return
     */
    @PostMapping(path = "/saveAdmin")
    public CommonResult saveAdmin(@RequestBody AdminEntity admin, @RequestHeader("adminToken") String adminToken) {
        CommonResult commonResult = null;
        if (admin.getId() == null) {
            adminService.saveAdmin(admin, adminToken);
        } else {
            adminService.update(admin, adminToken);
        }

        commonResult = new CommonResult(200, null, "保存成功");

        return commonResult;
    }

    /**
     * 通过id查找相应的信息，一般用于编辑时显示
     * @param id
     * @return
     */
    @GetMapping(path = "/findAdminByid/{id}")
    public CommonResult findAdminByid(@PathVariable("id") Integer id) {
        CommonResult commonResult = null;
        AdminEntity admin = adminService.findAdminByid(id);
        commonResult = new CommonResult(200, admin, "查询成功");

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
        adminService.delete(id);
        commonResult = new CommonResult(200, null, "删除成功");

        return commonResult;
    }


}
