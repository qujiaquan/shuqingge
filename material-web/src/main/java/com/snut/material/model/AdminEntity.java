package com.snut.material.model;

import lombok.Data;

/**
 * 用户/管理员
 */
@Data
public class AdminEntity {

    private Integer id;
    private String account;
    private String name;
    private String password;
    private String gender;
    private String email;
    private String phone;
    private String img;
    private Integer type;
    private String token;
    private Integer flag;
    private Double balance;
    private String time;
    private Integer code;
    private String operator;
    private String opertime;

}
