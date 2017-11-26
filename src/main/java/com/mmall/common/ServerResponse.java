package com.mmall.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * @author: zhongtianemail@gmail.com
 * @create 2017-11-08 下午3:02
 * @Description:
 **/
//下面注解确保序列化json的时候，如果是null的对象，key会消失
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {

    private int status;
    private String msg;
    /**
     * @author:zhongtianemail@gmail.com.cn
     * @Description:泛型'<T>'好处是返回的时候可以指定泛型里面的内容，也可以不指定泛型里面的强制类型；
     * 例如：正确的时候可能封装一个string，错误的时候封装的不是String类型（例如map或list）；
     * @Param:
     * @Return: 
     * @Date:   17-11-9 上午10:21
     */
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

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    @JsonIgnore//使之不在json序列化的结果之中
    public int getStatus(){
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData(){
        return data;
    }


    //
    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg, T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(String errorMessage){
        return new ServerResponse<T>(errorCode,errorMessage);
    }


    //    public static void main(String[] args) {
//        ServerResponse sr1 = new ServerResponse(1,new Object());
//        ServerResponse sr2 = new ServerResponse(1,"abc");
//        System.out.println("console");
//    }
}
