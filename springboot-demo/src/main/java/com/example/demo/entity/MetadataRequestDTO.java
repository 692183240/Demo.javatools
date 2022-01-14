package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author heshiqi
 * @data -22:11
 * @email caesarheshiqi@163.com
 */
@Data//可以为类提供读写功能，从而不用写get、set方法
@Builder//声明实体，表示可以进行Builder方式初始化
public class MetadataRequestDTO {

    private int schemaId;
    private int topicId;
    private String enName;
    private String cnName;
    private int tableType;
    private String remark;
    private int cycleDate;
    private String assestsLevel;
    private String storeFileFormat;
    private String projectId;
    private boolean dWLayer;
    private int layerType;
    private String layerTypeText;
    private String schemaName;
    private int sourceSchemaId;
    private String erjiName;
    private long nodeId;
}
