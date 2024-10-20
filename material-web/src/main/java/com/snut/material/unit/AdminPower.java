package com.snut.material.unit;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import com.snut.material.common.JWTUtil;

/**
 * 通过token获取管理员的权限
 */
@Component
public class AdminPower {
    public boolean getAdminPower(String adminToken){
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(adminToken);
        Integer type = tokenInfo.getClaim("type").asInt();
        if (type > 0){
            return true;
        }
        return false;
    }
}
