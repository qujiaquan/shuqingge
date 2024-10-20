package com.snut.material.controller.message;

import com.github.pagehelper.PageInfo;
import com.snut.material.model.NoticeResEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.snut.material.common.CommonResult;
import com.snut.material.service.message.NoticeResService;

@RestController
@RequestMapping(path = "/admin/noticeResCtl")
public class NoticeResController {

    @Autowired
    NoticeResService noticeResService;

    /**
     * 获取当前所有信息列表
     * @param num
     * @param pageSize
     * @return
     */
    @GetMapping(path = "/noticeRes/{num}/{pageSize}")
    public CommonResult adminList(@PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<NoticeResEntity> pageInfo = noticeResService.findNoticeResList(num, pageSize);

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
    @GetMapping(path = "/findNoticeRes/{account}/{num}/{pageSize}")
    public CommonResult findNoticeRes(@PathVariable("account") String account, @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<NoticeResEntity> pageInfo = noticeResService.findNoticeRes(account, num, pageSize);

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
        noticeResService.changeState(row, flag);
        commonResult = new CommonResult(200, null, "修改成功");
        return commonResult;
    }

    /**
     * 保存和新增信息
     * @param noticeRes
     * @param adminToken
     * @return
     */
    @PostMapping(path = "/saveNoticeRes")
    public CommonResult saveNoticeRes(@RequestBody NoticeResEntity noticeRes, @RequestHeader("adminToken") String adminToken) {
        CommonResult commonResult = null;
        if (noticeRes.getId() == null) {
//            noticeResService.saveNoticeRes(noticeRes);
        } else {
            noticeResService.update(noticeRes, adminToken);
        }

        commonResult = new CommonResult(200, null, "保存成功");

        return commonResult;
    }

    /**
     * 通过id查找相应的信息，一般用于编辑时显示
     * @param id
     * @return
     */
    @GetMapping(path = "/findNoticeResByid/{id}")
    public CommonResult findNoticeResByid(@PathVariable("id") Integer id) {
        CommonResult commonResult = null;
        NoticeResEntity noticeRes = noticeResService.findNoticeResByid(id);
        commonResult = new CommonResult(200, noticeRes, "查询成功");

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
        noticeResService.delete(id);
        commonResult = new CommonResult(200, null, "删除成功");

        return commonResult;
    }


}
