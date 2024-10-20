package com.snut.material.model;

import lombok.Data;

/**
 * 下载记录
 */
@Data
public class DownloadsEntity {

    private Integer id;
    private Integer userId;
    private String userName;
    /**
     * 素材id
     */
    private Integer materialId;
    private String materialTitle;
    private String img;
    private String downloadTime;

}
