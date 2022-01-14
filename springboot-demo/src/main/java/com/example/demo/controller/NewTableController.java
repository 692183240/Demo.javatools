package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.*;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.List;

/**
 * @author heshiqi
 * @data -21:20
 * @email caesarheshiqi@163.com
 */
@RestController//@RestController注解相当于@ResponseBody + @Controller合在一起的作用
                //在一个类上添加@Controller注解，表明了这个类是一个控制器类。但想要让这个类成为一个处理请求的处理器光有@Controller注解是不够的，他还需要进一步修炼才能成为一个处理器。
               /*@ResponseBody表示方法的返回值直接以指定的格式写入Http response body中，而不是解析为跳转路径。
        　  　格式的转换是通过HttpMessageConverter中的方法实现的，因为它是一个接口，因此由其实现类完成转换。
             如果要求方法返回的是json格式数据，而不是跳转页面，可以直接在类上标注@RestController，而不用在每个方法中标注@ResponseBody，简化了开发过程。*/
@RequestMapping("/node")//是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。
@Slf4j

//活的，调用俩个接口
public class NewTableController {
    //token易失效，及时修改
    private final String token = "JSESSIONID=8760F32B9696923C9B3E3024DC0C6EF8";

    @PostMapping("/create")//映射post请求
    public Resp<Void> create(@RequestBody CreateDto createDto){
        CreateDto createDto1 = CreateDto.builder()
                //.projectId("sjcas000001")//大科技
                .projectId("sjcas000022")
                .parentNodeId(99)
                .sceneType(3)
                .nodeType(11)
                .nodeName("test_heshiqi0185555")
                .schemaId(60335)
                .build();
        RestTemplate restTemplate = new RestTemplate();//新建一个对象
        String url = "http://10.251.129.24/data-rd-hub/api/common/node/create";//获取连接方式
        HttpHeaders headers = new HttpHeaders();
        //定义请求参数类型，这里用json所以是MediaType.APPLICATION_JSON
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("orgCode","sjcas000001");
        headers.add("token",token);//获取公共token
        HttpEntity<CreateDto> request = new HttpEntity<>(createDto, headers);
        String result = restTemplate.postForObject(url,request,String.class);
        log.info("create result:" + JSON.toJSONString(result));//日志输出

        return new Resp<>();//返回结果集
    }


    @PostMapping("/newSubmitTable")
    public Resp<Void> newSubmitTable(@RequestBody JsonRootBean jsonRootBean){

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://10.251.129.24/data-rd-hub/api/table/newSubmitTable";
        HttpHeaders headers = new HttpHeaders();
        //定义请求参数类型，这里用json所以是MediaType.APPLICATION_JSON
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("orgCode","sjcas000001");
        headers.add("token",token);//引用公共token
        log.info("jsonRootBean:"+ JSON.toJSONString(jsonRootBean));
        HttpEntity<JsonRootBean> request = new HttpEntity<>(jsonRootBean, headers);
        String result = restTemplate.postForObject(url,request,String.class);
        log.info("newSubmitTable result:" + JSON.toJSONString(result));//日志打印

        return new Resp<>();
    }

}
