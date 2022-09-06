package com.springboot.travel.exception;

public class SpringbootException extends Exception{
    private Integer code;
    private String msg;

    public SpringbootException(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public SpringbootException(SpringbootExceptionEnum exceptionEnum ){
        this(exceptionEnum.getCode() , exceptionEnum.getMsg());
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
