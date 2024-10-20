package com.snut.material.common;

import lombok.Data;

@Data
public class CommonResult {


    int code;
    Object data;
    String message;

    public CommonResult(int code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public CommonResult(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
