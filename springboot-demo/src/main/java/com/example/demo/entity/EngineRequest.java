package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author heshiqi
 * @data -20:42
 * @email caesarheshiqi@163.com
 */
@Data
@Builder
public class EngineRequest {
    private String Name;
    private int engineType;
    private List<Integer> envTypes;
    private List<Integer> moreMetaStatus;
    private int pageIndex;
    private int pageSize;
    private String projectId;
    private int totalCount;

}
