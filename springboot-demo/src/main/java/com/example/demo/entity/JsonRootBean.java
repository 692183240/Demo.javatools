package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author heshiqi
 * @data -22:10
 * @email caesarheshiqi@163.com
 */
@Data
@Builder
public class JsonRootBean {

    private MetadataRequestDTO metadataRequestDTO;
    private List<ColSchemaRequestDTOList> colSchemaRequestDTOList;

}
