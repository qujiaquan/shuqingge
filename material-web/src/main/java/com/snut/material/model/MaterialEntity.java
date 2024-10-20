package com.snut.material.model;

import lombok.Data;

/**
 * 素材表
 */
@Data
public class MaterialEntity {

    private Integer id;
    private String title;
    private String description;
    private String img;
    private Integer flag;
    private Integer userId;
    private String userName;
    private Integer userType;
    /**
     * 素材分类
     */
    private Integer categorieId;
    private String categorieName;
    private String uploadTime;
    /**
     * 素材格式
     */
    private Integer formatId;
    private String formatName;
    private String filePath;
    private String isFree;
    private Double price;
    private String operator;
    private String opertime;

    private Integer noticeReqId;

}
