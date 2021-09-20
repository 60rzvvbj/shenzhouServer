package com.ycx.shenzhou.controller;

import java.util.HashMap;
import java.util.LinkedList;

// 基本返回数据
public class BaseResult {
    private int code;
    private Object data;
    private boolean flag;
    private String message;

    public static BaseResult getSuccessBaseData() {
        return new BaseResult(200, true);
    }

    public static BaseResult getErrorBaseData() {
        return new BaseResult(500, false);
    }

    public BaseResult() {
        this(200, true);
    }

    public BaseResult(int code, boolean flag) {
        this.code = code;
        this.flag = flag;
        this.message = "";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseData{" +
                "code=" + code +
                ", data=" + data +
                ", flag=" + flag +
                ", message='" + message + '\'' +
                '}';
    }
}