package com.springboot.travel.exception;

public enum SpringbootExceptionEnum {
    NEED_USER_NAME(10002,"用户名不能为空"),
    NEED_USER_PASSWORD(10003,"密码不能为空"),
    PASSWORD_TOO_SHORT(10004,"密码长度不能小于8位"),
    NAME_EXISTED(10005,"用户名已存在,注册失败"),
    INSERT_FAILED(10006,"插入失败，请重新输入"),
    PASSWORD_ERROR(10007,"密码错误"),
    NEED_NO_EXISTED(10008,"用户不存在"),
    NEED_USER_ROLE(10009,"用户角色不能为空"),
    NEED_ADMIN_PASS(10010,"登录失败,请等待管理员审核"),
    UPDATE_STATE_ERROR(10011 , "用户状态更新失败"),
    USER_NOT_NULL(10012,"用户信息获取失败"),
    UPDATE_USER_ERROR(10013,"用户更新失败"),
    DELETE_ERROR(10014 , "删除失败"),
    UPDATE_ERROR(10015,"更新失败");

    Integer code;
    String msg;

    SpringbootExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
