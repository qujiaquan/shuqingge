package com.snut.material.model;

import lombok.Data;

/**
 * 素材分类
 */
@Data
public class CategorieEntity {

    private Integer id;
    private String name;
    /**
     * 素材格式
     */
    private Integer formatId;
    private String formatName;
    private String operator;
    private String opertime;

}
