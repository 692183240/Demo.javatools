package com.example.demo.entity;

import lombok.Data;

@Data
public class Resp<T> {
    /**
     * 错误描述
     */
    private String message;
    /**
     * 0表示成功
     */
    private int code;
    /**
     * true表示成功
     */
    private Boolean result;
    /**
     * API返回的数据
     */
    private T data;
}
