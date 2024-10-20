package com.snut.material.controller.front;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.snut.material.SpringbootApplication;
import com.snut.material.common.CommonResult;
import com.snut.material.common.JWTUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.Date;


/**
 * 资源上传类
 */
@RestController
@RequestMapping(path = "/front/uploadCtl")
public class UploadController {
    /**
     * 图片上传
     * @param file
     * @param adminToken
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/adminUpload")
    public CommonResult adminUpload(@RequestParam("file") CommonsMultipartFile file,
                               @RequestHeader("admintoken") String adminToken) throws IOException {
        CommonResult commonResult = null;
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(adminToken);
        int id = tokenInfo.getClaim("id").asInt();
        String account = tokenInfo.getClaim("account").asString();
        String path = SpringbootApplication.tomcatAddress + "admin\\"+account;
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
    /**
     * 图片上传
     * @param file
     * @param userToken
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/userUpload")
    public CommonResult userUpload(@RequestParam("file") CommonsMultipartFile file,
                               @RequestHeader("userToken") String userToken) throws IOException {
        CommonResult commonResult = null;
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(userToken);
        int id = tokenInfo.getClaim("id").asInt();
        String account = tokenInfo.getClaim("account").asString();
        String path = SpringbootApplication.tomcatAddress + "user\\"+account;
//            String path = "/opt/apache-tomcat-9.0.37/webapps/userFile/"+account;
        File file1 = new File(path);
        if(!file1.exists()){
            file1.mkdir();
        }
        String newfileName = new Date().getTime()+file.getOriginalFilename();
        path+="\\"+newfileName;
        file.transferTo(new File(path));
        //保存文件和账号的关系
        String filepath = "materialFile/user/"+account+"/"+newfileName;
        commonResult = new CommonResult(200,filepath,"上传成功");
        return commonResult;
    }

    // 后期换为userToken
    @PostMapping(value = "/uploadNewsImg")
    public CommonResult uploadNewsImg(@RequestParam("file") CommonsMultipartFile file,
                                      @RequestHeader("adminToken") String adminToken) throws IOException {
        CommonResult commonResult = null;
     /*   DecodedJWT tokenInfo = JWTUtil.getTokenInfo(adminToken);
        int id = tokenInfo.getClaim("id").asInt();
        String account = tokenInfo.getClaim("account").asString();*/
        String path = "F:\\Tomcat\\apache-tomcat-9.0.43\\webapps\\landFile\\newsimg";
//            String path = "/opt/apache-tomcat-9.0.37/webapps/landFile/newsimg/";
        File file1 = new File(path);

        if(!file1.exists()){
            file1.mkdir();
        }
        String newfileName = new Date().getTime()+file.getOriginalFilename();
        path+="\\"+newfileName;
        file.transferTo(new File(path));
        //保存文件和账号的关系
        String filepath = "landFile/newsimg/"+newfileName;
        commonResult = new CommonResult(200,filepath,"登录成功");
        return commonResult;
    }



}
