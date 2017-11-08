package com.mmall.common;

import java.io.Serializable;

/**
 * @author: zhongtianemail@gmail.com
 * @create 2017-11-08 下午3:02
 * @Description:
 **/
public class ServerResponse<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus(){
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData(){
        return data;
    }


    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }




    //    public static void main(String[] args) {
//        ServerResponse sr1 = new ServerResponse(1,new Object());
//        ServerResponse sr2 = new ServerResponse(1,"abc");
//        System.out.println("console");
//    }
}
