package com.snut.material.controller.message;

import com.github.pagehelper.PageInfo;
import com.snut.material.model.CategorieEntity;
import com.snut.material.model.FormatEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.snut.material.common.CommonResult;
import com.snut.material.service.message.CategorieService;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/categorieCtl")
public class CategorieController {

    @Autowired
    CategorieService categorieService;

    /**
     * 获取当前所有信息列表
     * @param num
     * @param pageSize
     * @return
     */
    @GetMapping(path = "/categorie/{num}/{pageSize}")
    public CommonResult adminList(@PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<CategorieEntity> pageInfo = categorieService.findCategorieList(num, pageSize);

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
    @GetMapping(path = "/findCategorie/{account}/{num}/{pageSize}")
    public CommonResult findCategorie(@PathVariable("account") String account, @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<CategorieEntity> pageInfo = categorieService.findCategorie(account, num, pageSize);

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
        categorieService.changeState(row, flag);
        commonResult = new CommonResult(200, null, "修改成功");
        return commonResult;
    }

    /**
     * 保存和新增信息
     * @param categorie
     * @param adminToken
     * @return
     */
    @PostMapping(path = "/saveCategorie")
    public CommonResult saveCategorie(@RequestBody CategorieEntity categorie, @RequestHeader("adminToken") String adminToken) {
        CommonResult commonResult = null;
        if (categorie.getId() == null) {
            categorieService.saveCategorie(categorie, adminToken);
        } else {
            categorieService.update(categorie, adminToken);
        }

        commonResult = new CommonResult(200, null, "保存成功");

        return commonResult;
    }

    /**
     * 通过id查找相应的信息，一般用于编辑时显示
     * @param id
     * @return
     */
    @GetMapping(path = "/findCategorieByid/{id}")
    public CommonResult findCategorieByid(@PathVariable("id") Integer id) {
        CommonResult commonResult = null;
        CategorieEntity categorie = categorieService.findCategorieByid(id);
        commonResult = new CommonResult(200, categorie, "查询成功");

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
        categorieService.delete(id);
        commonResult = new CommonResult(200, null, "删除成功");

        return commonResult;
    }

    @GetMapping(path = "/findCategorieByFormatId/{formatId}")
    public CommonResult findCategorieByFormatId(@PathVariable("formatId") Integer formatId) {
        CommonResult commonResult = null;
        List<FormatEntity> formatAll = categorieService.findCategorieByFormatId(formatId);

        commonResult = new CommonResult(200, formatAll, "查询成功");
        return commonResult;
    }



}
