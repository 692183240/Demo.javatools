package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author heshiqi
 * @data -21:26
 * @email caesarheshiqi@163.com
 */
@Data
@Builder
public class CreateDto {


    private String projectId;
    private int parentNodeId;
    private int sceneType;
    private int nodeType;
    private String nodeName;
    private int schemaId;

}
