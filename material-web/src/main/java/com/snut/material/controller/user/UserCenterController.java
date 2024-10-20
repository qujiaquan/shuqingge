package com.snut.material.controller.user;

import com.github.pagehelper.PageInfo;
import com.snut.material.common.CommonResult;
import com.snut.material.model.*;
import com.snut.material.service.message.*;
import com.snut.material.service.user.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user/userCenterCtl")
public class UserCenterController {

    @Autowired
    UserCenterService userCenterService;
    @Autowired
    UserService userService;
    @Autowired
    MaterialService materialService;
    @Autowired
    CommentService commentService;
    @Autowired
    DownloadsService downloadsService;
    @Autowired
    PaymentsService paymentsService;
    @Autowired
    CollectionService collectionService;
    @Autowired
    NoticeReqService noticeReqService;
    @Autowired
    NoticeResService noticeResService;

    @GetMapping(path = "/userCenterInfo/{id}")
    public CommonResult getUserByid(@PathVariable("id") Integer id) {
        CommonResult commonResult = null;
        UserEntity user = userService.findUserByid(id);
        commonResult = new CommonResult(200, user, "查询成功");
        return commonResult;
    }

    @PostMapping(path = "/saveUserInfo")
    public CommonResult saveUserInfo(@RequestBody UserEntity user, @RequestHeader("userToken") String userToken) {
        CommonResult commonResult = null;
        userService.update(user, userToken);
        commonResult = new CommonResult(200, null, "保存成功");

        return commonResult;
    }

    /**
     * 根据传来的userId查询相应信息
     * @param userId
     * @param num
     * @param pageSize
     * @return
     */
    @GetMapping(path = "/findMaterialsByUserId/{userId}/{num}/{pageSize}")
    public CommonResult findMaterialsByUserId(@PathVariable("userId") String userId, @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<MaterialEntity> pageInfo = materialService.findMaterialsByUserId(userId, num, pageSize);
        commonResult = new CommonResult(200, pageInfo, "查询成功");
        return commonResult;
    }

    /**
     * 根据传来的userId查询相应信息
     * @param userId
     * @param num
     * @param pageSize
     * @return
     */
    @GetMapping(path = "/findNoticeReqByUserId/{userId}/{num}/{pageSize}")
    public CommonResult findNoticeReqByUserId(@PathVariable("userId") String userId, @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<MaterialEntity> pageInfo = materialService.findNoticeReqByUserId(userId, num, pageSize);
        commonResult = new CommonResult(200, pageInfo, "查询成功");
        return commonResult;
    }

    /**
     * 根据传来的userId查询相应信息
     * @param userId
     * @param num
     * @param pageSize
     * @return
     */
    @GetMapping(path = "/findCollectionByUserId/{userId}/{num}/{pageSize}")
    public CommonResult findCollectionByUserId(@PathVariable("userId") String userId, @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<MaterialEntity> pageInfo = materialService.findCollectionByUserId(userId, num, pageSize);
        commonResult = new CommonResult(200, pageInfo, "查询成功");
        return commonResult;
    }

    /**
     * 根据传来的userId查询相应信息
     * @param userId
     * @param num
     * @param pageSize
     * @return
     */
    @GetMapping(path = "/findCommentByUserId/{userId}/{num}/{pageSize}")
    public CommonResult findCommentByUserId(@PathVariable("userId") String userId, @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<CommentEntity> pageInfo = commentService.findCommentByUserId(userId, num, pageSize);
        commonResult = new CommonResult(200, pageInfo, "查询成功");
        return commonResult;
    }

    /**
     * 根据传来的userId查询相应信息
     * @param userId
     * @param num
     * @param pageSize
     * @return
     */
    @GetMapping(path = "/findDownloadsByUserId/{userId}/{num}/{pageSize}")
    public CommonResult findDownloadsByUserId(@PathVariable("userId") String userId, @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<MaterialEntity> pageInfo = downloadsService.findDownloadsByUserId(userId, num, pageSize);
        commonResult = new CommonResult(200, pageInfo, "查询成功");
        return commonResult;
    }
    /**
     * 根据传来的userId查询相应信息
     * @param userId
     * @param num
     * @param pageSize
     * @return
     */
    @GetMapping(path = "/findPaymentByUserId/{userId}/{num}/{pageSize}")
    public CommonResult findPaymentByUserId(@PathVariable("userId") String userId, @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<MaterialEntity> pageInfo = paymentsService.findPaymentByUserId(userId, num, pageSize);
        commonResult = new CommonResult(200, pageInfo, "查询成功");
        return commonResult;
    }

    /**
     * 查询当前素材是否被当前用户收藏
     */
    @GetMapping(path = "/findUserIsCollection/{userId}/{materialId}")
    public CommonResult findUserIsCollection(@PathVariable("userId") Integer userId, @PathVariable("materialId") Integer materialId) {
        CommonResult commonResult = null;
        CollectionEntity collection = collectionService.findUserIsCollection(userId, materialId);
        if (collection == null) {
            commonResult = new CommonResult(200, 0, "没有收藏");
        }else {
            commonResult = new CommonResult(200, 1, "有收藏");
        }
        return commonResult;
    }

    /**
     * 用户收藏操作
     * @param collection
     * @return
     */
    @PostMapping(path = "/saveCollection")
    public CommonResult saveCollection(@RequestBody CollectionEntity collection) {
        CommonResult commonResult = null;
        collectionService.saveCollection(collection);
        commonResult = new CommonResult(200, null, "收藏成功");
        return commonResult;
    }

    /**
     * 删除收藏
     * @param
     * @return
     */
    @GetMapping(path = "/deleteCollection/{userId}/{materialId}")
    public CommonResult deleteCollection(@PathVariable("userId") Integer userId, @PathVariable("materialId") Integer materialId) {
        CommonResult commonResult = null;
        collectionService.deleteCollection(userId, materialId);
        commonResult = new CommonResult(200, null, "取消收藏成功");

        return commonResult;
    }
    /**
     * 查询当前素材的评论
     */
    @GetMapping(path = "/findMaterialComment/{userId}/{materialId}")
    public CommonResult findMaterialComment(@PathVariable("userId") Integer userId, @PathVariable("materialId") Integer materialId) {
        CommonResult commonResult = null;
        List<CommentEntity> comments = commentService.findMaterialComment(userId, materialId);
        commonResult = new CommonResult(200, comments, "查询成功");
        return commonResult;
    }


    /**
     * 用户评论操作
     * @return
     */
    @PostMapping(path = "/saveComment")
    public CommonResult saveComment(@RequestBody CommentEntity comment) {
        CommonResult commonResult = null;
        commentService.saveComment(comment);
        commonResult = new CommonResult(200, null, "评论成功");
        return commonResult;
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    @GetMapping(path = "/deleteComment/{id}")
    public CommonResult deleteComment(@PathVariable("id") Integer id) {
        CommonResult commonResult = null;
        commentService.delete(id);
        commonResult = new CommonResult(200, null, "删除成功");

        return commonResult;
    }

    /**
     * 用户充值
     */
    @GetMapping(path = "/userPay/{userId}/{num}")
    public CommonResult userPay(@PathVariable("userId") Integer userId, @PathVariable("num") Integer num) {
        CommonResult commonResult = null;
        userService.userPay(userId, num);
        commonResult = new CommonResult(200, null, "充值成功");
        return commonResult;
    }

    /**
     * 用户新增素材求助公告
     * @return
     */
    @PostMapping(path = "/saveNoticeReq")
    public CommonResult saveNoticeReq(@RequestBody NoticeReqEntity noticeReq) {
        CommonResult commonResult = null;
        noticeReqService.saveNoticeReq(noticeReq);
        commonResult = new CommonResult(200, null, "发布成功");
        return commonResult;
    }

    /**
     * 用户新增素材公告响应
     * @return
     */
    @PostMapping(path = "/saveNoticeRes")
    public CommonResult saveNoticeRes(@RequestBody MaterialEntity material) {
        CommonResult commonResult = null;
        noticeResService.saveNoticeRes(material);
        commonResult = new CommonResult(200, null, "发布成功");
        return commonResult;
    }

    /**
     * 删除素材
     * @param id
     * @return
     */
    @GetMapping(path = "/deleteMaterial/{id}")
    public CommonResult deleteMaterial(@PathVariable("id") Integer id) {
        CommonResult commonResult = null;
        materialService.delete(id);
        commonResult = new CommonResult(200, null, "删除成功");

        return commonResult;
    }

    /**
     * 删除素材求助
     * @param id
     * @return
     */
    @GetMapping(path = "/deleteNoticeReq/{id}")
    public CommonResult deleteNoticeReq(@PathVariable("id") Integer id) {
        CommonResult commonResult = null;
        noticeReqService.delete(id);
        commonResult = new CommonResult(200, null, "删除成功");

        return commonResult;
    }

    /**
     * 新增素材信息
     * @return
     */
    @PostMapping(path = "/saveMaterial")
    public CommonResult saveMaterial(@RequestBody MaterialEntity material, @RequestHeader("userToken") String userToken) {
        CommonResult commonResult = null;
        materialService.saveMaterial(material, userToken);
        commonResult = new CommonResult(200, null, "保存成功");

        return commonResult;
    }
    /**
     * 新增下载信息
     * @return
     */
    @PostMapping(path = "/saveDownload")
    public CommonResult saveDownload(@RequestBody DownloadsEntity download, @RequestHeader("userToken") String userToken) {
        CommonResult commonResult = null;
        downloadsService.saveDownloads(download, userToken);
        commonResult = new CommonResult(200, null, "保存成功");

        return commonResult;
    }
    /**
     * 新增支付信息
     * @return
     */
    @PostMapping(path = "/savePayments")
    public CommonResult savePayments(@RequestBody PaymentsEntity payments, @RequestHeader("userToken") String userToken) {
        CommonResult commonResult = null;
        paymentsService.savePayments(payments, userToken);
        commonResult = new CommonResult(200, null, "保存成功");

        return commonResult;
    }

    /**
     * 获取用户余额
     * @param id
     * @return
     */
    @GetMapping(path = "/findUser/{id}")
    public CommonResult findUser(@PathVariable("id") Integer id) {
        CommonResult commonResult = null;
        UserEntity user = userService.findUserByid(id);
        commonResult = new CommonResult(200, user, "删除成功");
        return commonResult;
    }

    /**
     * 通过id查看素材响应信息
     * @param id
     * @return
     */
    @GetMapping(path = "/findNoticeResByReqId/{id}")
    public CommonResult findNoticeResByReqId(@PathVariable("id") Integer id) {
        CommonResult commonResult = null;
        List<NoticeResEntity> noticeRes = noticeResService.findNoticeResByReqId(id);
        commonResult = new CommonResult(200, noticeRes, "查询成功");

        return commonResult;
    }



}
