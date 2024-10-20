package com.snut.material.controller.front;

import com.github.pagehelper.PageInfo;
import com.snut.material.common.CommonResult;
import com.snut.material.model.FormatEntity;
import com.snut.material.model.MaterialEntity;
import com.snut.material.model.NoticeReqEntity;
import com.snut.material.service.message.CategorieService;
import com.snut.material.service.message.MaterialService;
import com.snut.material.service.message.NoticeReqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/front/frontInfoCtl")
public class FrontInfoController {

    @Autowired
    MaterialService materialService;
    @Autowired
    NoticeReqService noticeReqService;
    @Autowired
    CategorieService categorieService;

    @GetMapping(path = "/newFirstMaterial")
    public CommonResult newFirstMaterial() {
        CommonResult commonResult = null;
        MaterialEntity material = materialService.getNewFirstMaterial();
        commonResult = new CommonResult(200, material, "查询成功");
        return commonResult;
    }
    @GetMapping(path = "/findMaterialsById/{id}")
    public CommonResult findMaterialsById(@PathVariable("id") Integer id) {
        CommonResult commonResult = null;
        MaterialEntity material = materialService.findMaterialByid(id);
        if (material == null) {
            commonResult = new CommonResult(201, material, "该素材已被删除");
        }else{
            commonResult = new CommonResult(200, material, "查询成功");
        }

        return commonResult;
    }

    /**
     * 根据传来的formatId查询相应信息
     * @param formatId
     * @param num
     * @param pageSize
     * @return
     */
    @GetMapping(path = "/findMaterialsByFormatId/{formatId}/{num}/{pageSize}")
    public CommonResult findMaterial(@PathVariable("formatId") String formatId, @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<MaterialEntity> pageInfo = materialService.findMaterialsByFormatId(formatId, num, pageSize);
        commonResult = new CommonResult(200, pageInfo, "查询成功");
        return commonResult;
    }


    /**
     * 查询素材求助
     * @param num
     * @param pageSize
     * @return
     */
    @GetMapping(path = "/findHelp/{num}/{pageSize}")
    public CommonResult findHelp( @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<NoticeReqEntity> pageInfo = noticeReqService.findNoticeReqList(num, pageSize);
        commonResult = new CommonResult(200, pageInfo, "查询成功");
        return commonResult;
    }
    /**
     * 根据传来的material查询相应信息
     * @param materialId
     * @param num
     * @param pageSize
     * @return
     */
    @GetMapping(path = "/findMaterialInfoByUserId/{materialId}/{num}/{pageSize}")
    public CommonResult findMaterialInfoByUserId(@PathVariable("materialId") int materialId, @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        MaterialEntity material = materialService.findMaterialInfoByUserId(materialId);
        commonResult = new CommonResult(200, material, "查询成功");
        return commonResult;
    }
    @GetMapping(path = "/findCategorieByFormatId/{formatId}")
    public CommonResult findCategorieByFormatId(@PathVariable("formatId") Integer formatId) {
        CommonResult commonResult = null;
        List<FormatEntity> formatAll = categorieService.findCategorieByFormatId(formatId);

        commonResult = new CommonResult(200, formatAll, "查询成功");
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
    public CommonResult findMaterial1(@PathVariable("account") String account, @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<MaterialEntity> pageInfo = materialService.findMaterial(account, num, pageSize);

        commonResult = new CommonResult(200, pageInfo, "查询成功");
        return commonResult;
    }
    @GetMapping(path = "/findMaterialByFormatIdAndCategorieId/{formatId}/{categorieId}/{num}/{pageSize}")
    public CommonResult findMaterialByFormatIdAndCategorieId(@PathVariable("formatId") String formatId,@PathVariable("categorieId") String categorieId, @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<MaterialEntity> pageInfo = materialService.findMaterialByFormatIdAndCategorieId(formatId, categorieId, num, pageSize);

        commonResult = new CommonResult(200, pageInfo, "查询成功");
        return commonResult;
    }

    @GetMapping(path = "/findMaterialByFormatIdAndIsFree/{formatId}/{isFree}/{num}/{pageSize}")
    public CommonResult findMaterialByFormatIdAndIsFree(@PathVariable("formatId") String formatId,@PathVariable("isFree") String isFree, @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<MaterialEntity> pageInfo = materialService.findMaterialByFormatIdAndIsFree(formatId, isFree, num, pageSize);

        commonResult = new CommonResult(200, pageInfo, "查询成功");
        return commonResult;
    }



}
