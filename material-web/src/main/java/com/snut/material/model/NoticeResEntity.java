package com.snut.material.model;

import lombok.Data;

/**
 * 素材求助公告响应
 */
@Data
public class NoticeResEntity {

    private Integer id;
    private String title;
    private String description;
    private Integer userId;
    private String userName;
    private Integer noticeReqId;
    private String noticeReqTitle;
    /**
     * 对应素材
     */
    private Integer materialId;
    private String materialTitle;
    private Integer flag;
    private String resTime;
    private String operator;
    private String opertime;
}
