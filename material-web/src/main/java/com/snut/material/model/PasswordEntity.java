package com.snut.material.model;

import lombok.Data;

@Data
public class PasswordEntity {

    private Integer id;
    private String oldPassword;
    private String newPassword1;
    private String newPassword2;

}
