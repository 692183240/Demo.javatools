package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author heshiqi
 * @data -17:26
 * @email caesarheshiqi@163.com
 */
@Data
@Builder
public class ESCreateDto {

        public String colEnName;
        public String esFieldType;
        public String analyzer;
        public String colZoneFormat;


        public String enName;

        public String engineVersion;

        public String esDatasourceId;

        public int esDynamicType;

        public int esReplicasNum;

        public int esShardsNum;

        public String esType;

        public int nodeId;

        public String projectId;

        public String orgCode;

}
