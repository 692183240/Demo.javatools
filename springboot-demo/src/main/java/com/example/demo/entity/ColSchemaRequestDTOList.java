package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author heshiqi
 * @data -22:12
 * @email caesarheshiqi@163.com
 */
@Data
@Builder
public class ColSchemaRequestDTOList {

    private String index;
    private String remark;
    private String colEnName;
    private String colCnName;
    private String colLen;
    private String colType;
    private int pkType;
    private int colZoneType;
    private int metaStatus;
    private int sortNum;
}
