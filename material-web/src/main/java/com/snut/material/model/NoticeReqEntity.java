package com.snut.material.model;

import lombok.Data;

/**
 * 素材求助公告
 */
@Data
public class NoticeReqEntity {

    private Integer id;
    private String title;
    private String description;
    private Integer userId;
    private String userName;
    private Integer flag;
    private String reqTime;

}
