package com.snut.material.controller.message;

import com.github.pagehelper.PageInfo;
import com.snut.material.model.FormatEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.snut.material.common.CommonResult;
import com.snut.material.service.message.FormatService;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/formatCtl")
public class FormatController {

    @Autowired
    FormatService formatService;

    /**
     * 获取当前所有信息列表
     * @param num
     * @param pageSize
     * @return
     */
    @GetMapping(path = "/format/{num}/{pageSize}")
    public CommonResult adminList(@PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<FormatEntity> pageInfo = formatService.findFormatList(num, pageSize);

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
    @GetMapping(path = "/findFormat/{account}/{num}/{pageSize}")
    public CommonResult findFormat(@PathVariable("account") String account, @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<FormatEntity> pageInfo = formatService.findFormat(account, num, pageSize);

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
        formatService.changeState(row, flag);
        commonResult = new CommonResult(200, null, "修改成功");
        return commonResult;
    }

    /**
     * 保存和新增信息
     * @param format
     * @param adminToken
     * @return
     */
    @PostMapping(path = "/saveFormat")
    public CommonResult saveFormat(@RequestBody FormatEntity format, @RequestHeader("adminToken") String adminToken) {
        CommonResult commonResult = null;
        if (format.getId() == null) {
            formatService.saveFormat(format, adminToken);
        } else {
            formatService.update(format, adminToken);
        }

        commonResult = new CommonResult(200, null, "保存成功");

        return commonResult;
    }

    /**
     * 通过id查找相应的信息，一般用于编辑时显示
     * @param id
     * @return
     */
    @GetMapping(path = "/findFormatByid/{id}")
    public CommonResult findFormatByid(@PathVariable("id") Integer id) {
        CommonResult commonResult = null;
        FormatEntity format = formatService.findFormatByid(id);
        commonResult = new CommonResult(200, format, "查询成功");

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
        formatService.delete(id);
        commonResult = new CommonResult(200, null, "删除成功");

        return commonResult;
    }

    @GetMapping(path = "/findFormatAll")
    public CommonResult findFormatAll() {
        CommonResult commonResult = null;
        List<FormatEntity> formatAll = formatService.findFormatAll();

        commonResult = new CommonResult(200, formatAll, "查询成功");
        return commonResult;
    }


}
