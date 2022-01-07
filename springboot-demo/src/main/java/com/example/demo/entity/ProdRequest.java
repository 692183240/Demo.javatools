package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author heshiqi
 * @data -21:43
 * @email caesarheshiqi@163.com
 */
@Data
@Builder
public class ProdRequest {
    private String detailUrl;
    private int metadataId;
    private String orgCode;
}
