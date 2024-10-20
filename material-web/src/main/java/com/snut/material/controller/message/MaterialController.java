package com.snut.material.controller.message;

import com.github.pagehelper.PageInfo;
import com.snut.material.model.MaterialEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.snut.material.common.CommonResult;
import com.snut.material.service.message.MaterialService;

@RestController
@RequestMapping(path = "/admin/materialCtl")
public class MaterialController {

    @Autowired
    MaterialService materialService;

    /**
     * 获取当前所有信息列表
     * @param num
     * @param pageSize
     * @return
     */
    @GetMapping(path = "/material/{num}/{pageSize}")
    public CommonResult adminList(@PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<MaterialEntity> pageInfo = materialService.findMaterialList(num, pageSize);

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
    @GetMapping(path = "/findMaterial/{account}/{num}/{pageSize}")
    public CommonResult findMaterial(@PathVariable("account") String account, @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<MaterialEntity> pageInfo = materialService.findMaterial(account, num, pageSize);

        commonResult = new CommonResult(200, pageInfo, "查询成功");
        return commonResult;
    }



    @GetMapping(path = "/findMaterialByFormatId/{formatId}/{categorieId}/{num}/{pageSize}")
    public CommonResult findMaterialByFormatId(@PathVariable("formatId") String formatId,@PathVariable("categorieId") String categorieId, @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<MaterialEntity> pageInfo = materialService.findMaterialByFormatIdAndCategorieId(formatId, categorieId, num, pageSize);

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
        materialService.changeState(row, flag);
        commonResult = new CommonResult(200, null, "修改成功");
        return commonResult;
    }

    /**
     * 保存和新增信息
     * @param material
     * @param adminToken
     * @return
     */
    @PostMapping(path = "/saveMaterial")
    public CommonResult saveMaterial(@RequestBody MaterialEntity material, @RequestHeader("adminToken") String adminToken) {
        CommonResult commonResult = null;
        if (material.getId() == null) {
            materialService.saveMaterial(material, adminToken);
        } else {
            materialService.update(material, adminToken);
        }

        commonResult = new CommonResult(200, null, "保存成功");

        return commonResult;
    }

    /**
     * 通过id查找相应的信息，一般用于编辑时显示
     * @param id
     * @return
     */
    @GetMapping(path = "/findMaterialByid/{id}")
    public CommonResult findMaterialByid(@PathVariable("id") Integer id) {
        CommonResult commonResult = null;
        MaterialEntity material = materialService.findMaterialByid(id);
        commonResult = new CommonResult(200, material, "查询成功");

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
        materialService.delete(id);
        commonResult = new CommonResult(200, null, "删除成功");

        return commonResult;
    }


}
