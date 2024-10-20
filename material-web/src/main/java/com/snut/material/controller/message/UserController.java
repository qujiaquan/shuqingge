package com.snut.material.controller.message;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.PageInfo;
import com.snut.material.common.JWTUtil;
import com.snut.material.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.snut.material.common.CommonResult;
import com.snut.material.service.message.UserService;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static com.snut.material.SpringbootApplication.tomcatAddress;

@RestController
@RequestMapping(path = "/admin/userCtl")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 获取当前所有信息列表
     * @param num
     * @param pageSize
     * @return
     */
    @GetMapping(path = "/user/{num}/{pageSize}")
    public CommonResult adminList(@PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<UserEntity> pageInfo = userService.findUserList(num, pageSize);

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
    @GetMapping(path = "/findUser/{account}/{num}/{pageSize}")
    public CommonResult findUser(@PathVariable("account") String account, @PathVariable("num") Integer num, @PathVariable("pageSize") Integer pageSize) {
        CommonResult commonResult = null;
        PageInfo<UserEntity> pageInfo = userService.findUser(account, num, pageSize);

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
        userService.changeState(row, flag);
        commonResult = new CommonResult(200, null, "修改成功");
        return commonResult;
    }

    /**
     * 保存和新增信息
     * @param user
     * @param adminToken
     * @return
     */
    @PostMapping(path = "/saveUser")
    public CommonResult saveUser(@RequestBody UserEntity user, @RequestHeader("adminToken") String adminToken) {
        CommonResult commonResult = null;
        if (user.getId() == null) {
            userService.saveUser(user, adminToken);
        } else {
            userService.update(user, adminToken);
        }

        commonResult = new CommonResult(200, null, "保存成功");

        return commonResult;
    }

    /**
     * 通过id查找相应的信息，一般用于编辑时显示
     * @param id
     * @return
     */
    @GetMapping(path = "/findUserByid/{id}")
    public CommonResult findUserByid(@PathVariable("id") Integer id) {
        CommonResult commonResult = null;
        UserEntity user = userService.findUserByid(id);
        commonResult = new CommonResult(200, user, "查询成功");

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
        userService.delete(id);
        commonResult = new CommonResult(200, null, "删除成功");

        return commonResult;
    }


    /**
     * 图片上传
     * @param file
     * @param adminToken
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/upload")
    public CommonResult upload(@RequestParam("file") CommonsMultipartFile file,
                               @RequestHeader("admintoken") String adminToken) throws IOException {
        CommonResult commonResult = null;
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(adminToken);
        int id = tokenInfo.getClaim("id").asInt();
        String account = tokenInfo.getClaim("account").asString();
        String path = tomcatAddress + "admin\\"+account;
//            String path = "/opt/apache-tomcat-9.0.37/webapps/userFile/"+account;
        File file1 = new File(path);
        if(!file1.exists()){
            file1.mkdir();
        }
        String newfileName = new Date().getTime()+file.getOriginalFilename();
        path+="\\"+newfileName;
        file.transferTo(new File(path));
        //保存文件和账号的关系
        String filepath = "materialFile/admin/"+account+"/"+newfileName;
        commonResult = new CommonResult(200,filepath,"上传成功");
        return commonResult;
    }

}
