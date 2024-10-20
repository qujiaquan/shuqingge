package com.snut.material.unit;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取当前标准格式的时间
 */
public class NowTime {
    public static String getNowTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String time = simpleDateFormat.format(date);
        return time;
    }
}
