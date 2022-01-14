package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.CreateDto;
import com.example.demo.entity.ESCreateDto;
import com.example.demo.entity.MetadataRequestDTO;
import com.example.demo.entity.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author heshiqi
 * @data -17:09
 * @email caesarheshiqi@163.com
 */

@Slf4j
@RestController
@RequestMapping("/node")

public class ESTableController {
    private final String token = "JSESSIONID=11F674094075BE93B1A1D774761027DD";
    @PostMapping("/create3")

    public Resp<Void> saveESTable(){
        ESCreateDto esCreateDto1 =ESCreateDto.builder()
                .colEnName("a")//表结构设计字段名
                .esFieldType("string")//字段类型
                .colZoneFormat("")//日期格式
                .analyzer("standard")//分词器
                .build();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://10.251.129.24/data-rd-hub/api/table/saveESTable";//调用接口地址
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("orgCode","sjcas000001");//添加组织组织ID
        headers.add("token",token);
        HttpEntity<ESCreateDto> request = new HttpEntity<>(esCreateDto1, headers);
        JSONObject result = restTemplate.postForObject(url,request, JSONObject.class);
        log.info("create result:" + result);//日志输出
        Integer nodeId = null;
        if (result.containsKey("data")){
            nodeId = result.getInteger("data");
        }

        ESCreateDto esCreateDto = ESCreateDto.builder()
                .enName("test_heshiqi")//索引名
                .engineVersion("1")//
                .esDatasourceId("39007")//数据源
                .esDynamicType(1)//索引类型   1 普通表 2 动态表
                .esReplicasNum(2)//分片cx
                .esShardsNum(2)//副本
                .esType("doc")
                .nodeId(nodeId)
                .projectId("sjcas00001")
                .orgCode("sjcas00001")
                .build();
        return new Resp<>();
    }
}
