
package com.snut.material.model;

import lombok.Data;

/**
 * 用户收藏
 */
@Data
public class CollectionEntity {

    private Integer id;
    private Integer userId;
    private String userName;
    /**
     * 素材id
     */
    private Integer materialId;
    private String materialTitle;
    private String img;
    private String time;
}
