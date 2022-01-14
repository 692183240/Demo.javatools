package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author heshiqi
 * @data -22:36
 * @email caesarheshiqi@163.com
 */
@Data//可以为类提供读写功能，从而不用写get、set方法
@Builder//声明实体，表示可以进行Builder方式初始化
public class ProcessTest {
    private Integer id;
    private String msg;
    private String token;
}
