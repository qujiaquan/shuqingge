package com.snut.material.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import com.snut.material.common.CommonResult;
import com.snut.material.common.JWTUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

//后端token验证拦截器

public class TokenInterceptorUser implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String usertoken = request.getHeader("userToken");

        if (usertoken != null) {
            boolean res = JWTUtil.verify(usertoken);
            System.out.println(usertoken);
            System.out.println("验证usertoken" + res);
            if (!res) {
                PrintWriter out = response.getWriter();
                CommonResult commonResult = new CommonResult(220, null, "token验证失败，用户信息有误");
                ObjectMapper objectMapper = new ObjectMapper();
                out.print(objectMapper.writeValueAsString(commonResult));//jackson组件转json
                out.close();
            }
            return res;
        }
        return true;
    }
}
