package com.snut.material.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snut.material.common.CommonResult;
import com.snut.material.common.JWTUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

//后端token验证拦截器

public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String admintoken = request.getHeader("adminToken");

        if (admintoken == null) {
            boolean res = JWTUtil.verify(admintoken);
            System.out.println(admintoken);
            System.out.println("验证admintoken" + res);
            if (!res) {
                PrintWriter out = response.getWriter();
                CommonResult commonResult = new CommonResult(210, null, "token验证失败，管理员信息有误");
                ObjectMapper objectMapper = new ObjectMapper();
                out.print(objectMapper.writeValueAsString(commonResult));//jackson组件转json
                out.close();
            }
            return res;
        }
        return true;
    }
}
