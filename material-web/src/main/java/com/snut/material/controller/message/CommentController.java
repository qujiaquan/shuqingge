package com.snut.material.controller.message;

import com.github.pagehelper.PageInfo;
import com.snut.material.model.CommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.snut.material.common.CommonResult;
import com.snut.material.service.message.CommentService;

@RestController
@RequestMapping(path = "/admin/commentCtl")
public class CommentController {

    @Autowired
    CommentService commentService;

    /**
     * 获取当前所有信息列表
     * @param num
     * @param pageSize
     * @return
     */
    @GetMapping(path = "/comment/{num}/{pageSize}")
    public CommonResult adminList(@PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<CommentEntity> pageInfo = commentService.findCommentList(num, pageSize);

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
    @GetMapping(path = "/findComment/{account}/{num}/{pageSize}")
    public CommonResult findComment(@PathVariable("account") String account, @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<CommentEntity> pageInfo = commentService.findComment(account, num, pageSize);

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
        commentService.changeState(row, flag);
        commonResult = new CommonResult(200, null, "修改成功");
        return commonResult;
    }

    /**
     * 保存和新增信息
     * @param comment
     * @param adminToken
     * @return
     */
    @PostMapping(path = "/saveComment")
    public CommonResult saveComment(@RequestBody CommentEntity comment, @RequestHeader("adminToken") String adminToken) {
        CommonResult commonResult = null;
        if (comment.getId() == null) {
            commentService.saveComment(comment);
        } else {
            commentService.update(comment, adminToken);
        }

        commonResult = new CommonResult(200, null, "保存成功");

        return commonResult;
    }

    /**
     * 通过id查找相应的信息，一般用于编辑时显示
     * @param id
     * @return
     */
    @GetMapping(path = "/findCommentByid/{id}")
    public CommonResult findCommentByid(@PathVariable("id") Integer id) {
        CommonResult commonResult = null;
        CommentEntity comment = commentService.findCommentByid(id);
        commonResult = new CommonResult(200, comment, "查询成功");

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
        commentService.delete(id);
        commonResult = new CommonResult(200, null, "删除成功");

        return commonResult;
    }


}
