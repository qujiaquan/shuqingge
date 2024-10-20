package com.snut.material.model;

import lombok.Data;

/**
 * 素材评论
 */
@Data
public class CommentEntity {

    private Integer id;
    private String comment;
    private Integer userId;
    private String userName;
    private String userImg;
    /**
     * 素材id
     */
    private Integer materialId;
    private String materialTitle;
    private String time;

}
