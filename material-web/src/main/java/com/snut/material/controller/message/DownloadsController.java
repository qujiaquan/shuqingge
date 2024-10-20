package com.snut.material.controller.message;

import com.github.pagehelper.PageInfo;
import com.snut.material.model.DownloadsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.snut.material.common.CommonResult;
import com.snut.material.service.message.DownloadsService;

@RestController
@RequestMapping(path = "/admin/downloadsCtl")
public class DownloadsController {

    @Autowired
    DownloadsService downloadsService;

    /**
     * 获取当前所有信息列表
     * @param num
     * @param pageSize
     * @return
     */
    @GetMapping(path = "/downloads/{num}/{pageSize}")
    public CommonResult adminList(@PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<DownloadsEntity> pageInfo = downloadsService.findDownloadsList(num, pageSize);

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
    @GetMapping(path = "/findDownloads/{account}/{num}/{pageSize}")
    public CommonResult findDownloads(@PathVariable("account") String account, @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<DownloadsEntity> pageInfo = downloadsService.findDownloads(account, num, pageSize);

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
        downloadsService.changeState(row, flag);
        commonResult = new CommonResult(200, null, "修改成功");
        return commonResult;
    }

    /**
     * 保存和新增信息
     * @param downloads
     * @param adminToken
     * @return
     */
    @PostMapping(path = "/saveDownloads")
    public CommonResult saveDownloads(@RequestBody DownloadsEntity downloads, @RequestHeader("adminToken") String adminToken) {
        CommonResult commonResult = null;
        if (downloads.getId() == null) {
            downloadsService.saveDownloads(downloads, adminToken);
        } else {
            downloadsService.update(downloads, adminToken);
        }

        commonResult = new CommonResult(200, null, "保存成功");

        return commonResult;
    }

    /**
     * 通过id查找相应的信息，一般用于编辑时显示
     * @param id
     * @return
     */
    @GetMapping(path = "/findDownloadsByid/{id}")
    public CommonResult findDownloadsByid(@PathVariable("id") Integer id) {
        CommonResult commonResult = null;
        DownloadsEntity downloads = downloadsService.findDownloadsByid(id);
        commonResult = new CommonResult(200, downloads, "查询成功");

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
        downloadsService.delete(id);
        commonResult = new CommonResult(200, null, "删除成功");

        return commonResult;
    }


}
