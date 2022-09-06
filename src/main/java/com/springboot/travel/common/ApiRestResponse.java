package com.springboot.travel.common;

import com.springboot.travel.exception.SpringbootExceptionEnum;

public class ApiRestResponse<T> {

    private Integer status;

    private String msg;

    private T data;

    private static final int OK_CODE = 10001;

    private static final String OK_MSG = "SUCCESS";

    public ApiRestResponse(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ApiRestResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ApiRestResponse() {
        this(OK_CODE,OK_MSG);
    }

    public static <T> ApiRestResponse<T> success(){
        return new ApiRestResponse<>();
    }

    public static <T> ApiRestResponse<T> success(T result){
        ApiRestResponse<T> apiRestResponse = new ApiRestResponse<>();
        apiRestResponse.setData(result);
        return apiRestResponse;
    }

    public static <T> ApiRestResponse<T> error(Integer code , String msg){
        return new ApiRestResponse<T>(code,msg);
    }

    public static <T> ApiRestResponse<T> error(SpringbootExceptionEnum ex){
        return new ApiRestResponse<T>(ex.getCode(),ex.getMsg());
    }

    @Override
    public String toString() {
        return "ApiRestResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static int getOkCode() {
        return OK_CODE;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public static String getOkMsg() {
        return OK_MSG;
    }
}
