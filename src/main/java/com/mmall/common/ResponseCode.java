package com.mmall.common;

/**
 * @author: zhongtianemail@gmail.com
 * @create 2017-11-08 下午4:44
 * @Description:
 **/
public enum  ResponseCode {

    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLLEGAL_ARGUMENT(10,"ILLLEGAL_ARGUMENT");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}


