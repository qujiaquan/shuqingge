
package com.snut.material.model;

import lombok.Data;

/**
 * 支付记录表
 */
@Data
public class PaymentsEntity {

    private Integer id;
    private Integer userId;
    private String userName;
    /**
     * 素材id
     */
    private Integer materialId;
    private String materialTitle;
    private Double amount;
    private String paymentTime;

}
