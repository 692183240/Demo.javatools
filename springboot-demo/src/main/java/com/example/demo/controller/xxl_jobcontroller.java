package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Resp;
import com.example.demo.entity.XxlDto;
import com.example.demo.entity.XxlProcess;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping
@Slf4j
public class xxl_jobcontroller {

    public XxlProcess process = new XxlProcess();

    private String token = "";

    @PostMapping("/inject")
    //新建任务接口
    public Resp<Void> create(@RequestParam Integer num) throws Exception {

        String url = "http://172.16.100.225:50101/xxl-job-admin/jobinfo/add";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();  //调用http请求
        //定义请求参数类型
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);//post提交数据 APPLICATION_FORM_URLENCODED_VALUE
        headers.setConnection("keep-alive");
        headers.set("Cookie", "XXL_JOB_LOGIN_IDENTITY=7b226964223a312c22757365726e616d65223a2261646d696e222c2270617373776f7264223a226531363538643536373063643732626631323439356539626563643635626134222c22726f6c65223a312c227065726d697373696f6e223a6e756c6c7d");
        //抓取的前端接口，需要带入Cookie


        int count = 1;
        String result = null;
        while (count <= num) {
            if (num >= 10000) {
                System.out.println("执行失败");
                break;
            } else {
                System.out.println("成功执行");
            }
            //循环10遍
            MultiValueMap<String, Object> forms = new LinkedMultiValueMap();
            forms.add("jobGroup", 5);
            forms.add("jobDesc", "测试数据" + count);
            forms.add("author", "heshiqiqi");
            forms.add("alarmEmail", "caesarheshiqi@163.com");
            forms.add("scheduleType", "CRON");
            forms.add("scheduleConf", "0 * * * * ?");
            forms.add("cronGen_display", "0 * * * * ?");
            forms.add("schedule_conf_Cron", "0 * * * * ?");
            forms.add("glueType", "BEAN");
            forms.add("executorHandler", "heshiqiqi");
            forms.add("executorRouteStrategy", "FIRST");
            forms.add("misfireStrategy", "DO_NOTHING");
            forms.add("executorBlockStrategy", "SERIAL_EXECUTION");
            forms.add("glueRemark", "GLUE代码初始化");

            log.info("forms:" + JSONObject.toJSONString(forms));

            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(forms, headers);


            result = restTemplate.postForObject(url, request, String.class);
            /*String result = restTemplate.postForObject(url, request, String.class);*/
            System.out.println("result:" + JSONObject.toJSONString(result));
            String content = "";
        /*if ("200".equals(result.getString("code"))) {
            content = result.getString("content");
        }*/
            if (null != result){
                if (null!= process ){
                    JSONObject jsonObject1 =JSONObject.parseObject(result);
                    if (!StringUtils.isEmpty(jsonObject1.get("content").toString()) ){
                        if (null ==process.getProcessId()){
                            List<String> list = new ArrayList<>();
                            list.add(jsonObject1.get("content").toString());
                            process.setProcessId(list);
                        }else{
                            process.getProcessId().add(jsonObject1.get("content").toString());
                        }
                    }
                }
            }
            log.info("新增任务执行");
            count++;
        }

        if (result != null) {
            System.out.println("result:" + JSONObject.toJSONString(result));
            System.out.println("=================================================数据生成成功"+num+"条=================================================");
        } else {
            System.out.println("=================================================数据未生成========================================================");
        }
        /*if (request >0||request!=null){
            System.out.println("==============================数据生成" + num + "条=======================================");
        }else{
            System.out.println("=================================数据生成失败============================================");
        }*/

        return new Resp<>();

    }


    @PostMapping("/start")
    //启动任务接口
    public Resp<Void> create2() throws Exception {

        String url = "http://172.16.100.225:50101/xxl-job-admin/jobinfo/start";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();  //调用http请求
        //定义请求参数类型
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);//post提交数据 APPLICATION_FORM_URLENCODED_VALUE
        headers.setConnection("keep-alive");
        headers.set("Cookie", "XXL_JOB_LOGIN_IDENTITY=7b226964223a312c22757365726e616d65223a2261646d696e222c2270617373776f7264223a226531363538643536373063643732626631323439356539626563643635626134222c22726f6c65223a312c227065726d697373696f6e223a6e756c6c7d");
        //抓取的前端接口，需要带入Cookie


        String result1 = null;
        if (null !=process.getProcessId()){
            if (process.getProcessId().size() >0){
                for (String p:process.getProcessId()) {
                    MultiValueMap<String, Object> forms = new LinkedMultiValueMap();
                    forms.add("id", p);
                    log.info("forms:" + JSONObject.toJSONString(forms));
                    HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(forms, headers);
                    result1 = restTemplate.postForObject(url, request, String.class);
                    /*String result = restTemplate.postForObject(url, request, String.class);*/
                    System.out.println("result:" + JSONObject.toJSONString(result1));
                    String content = "";
                    if (result1 != null) {
                        System.out.println("启动成功" + result1 + "条");
                        System.out.println("result:" + JSONObject.toJSONString(result1));
                    } else {
                        System.out.println("启动失败");
                    }
                }
            }
        }


        return new Resp<>();
    }


    @PostMapping("/stop")
    //停止任务接口
    public Resp<Void> create3() throws Exception {

        String url = "http://172.16.100.225:50101/xxl-job-admin/jobinfo/stop";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();  //调用http请求
        //定义请求参数类型
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);//post提交数据 APPLICATION_FORM_URLENCODED_VALUE
        headers.setConnection("keep-alive");
        headers.set("Cookie", "XXL_JOB_LOGIN_IDENTITY=7b226964223a312c22757365726e616d65223a2261646d696e222c2270617373776f7264223a226531363538643536373063643732626631323439356539626563643635626134222c22726f6c65223a312c227065726d697373696f6e223a6e756c6c7d");
        //抓取的前端接口，需要带入Cookie


        String result1 = null;
        if (null != process.getProcessId()) {
            if (process.getProcessId().size() > 0) {
                for (String p : process.getProcessId()) {
                    MultiValueMap<String, Object> forms = new LinkedMultiValueMap();
                    forms.add("id", p);
                    log.info("forms:" + JSONObject.toJSONString(forms));
                    HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(forms, headers);
                    result1 = restTemplate.postForObject(url, request, String.class);
                    /*String result = restTemplate.postForObject(url, request, String.class);*/
                    System.out.println("result:" + JSONObject.toJSONString(result1));
                    String content = "";
                    if (result1 != null) {
                        System.out.println("停止成功" + "forms" + "/n");
                        System.out.println("result:" + JSONObject.toJSONString(result1));
                    } else {
                        System.out.println("停止失败");
                    }
                }
            }
        }
        return new Resp<>();
    }


    @PostMapping("/remove")
    public Resp<Void> create4() throws Exception {

        String url = "http://172.16.100.225:50101/xxl-job-admin/jobinfo/remove";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();  //调用http请求
        //定义请求参数类型
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);//post提交数据 APPLICATION_FORM_URLENCODED_VALUE
        headers.setConnection("keep-alive");
        headers.set("Cookie", "XXL_JOB_LOGIN_IDENTITY=7b226964223a312c22757365726e616d65223a2261646d696e222c2270617373776f7264223a226531363538643536373063643732626631323439356539626563643635626134222c22726f6c65223a312c227065726d697373696f6e223a6e756c6c7d");
        //抓取的前端接口，需要带入Cookie


        String result1 = null;
        if (null != process.getProcessId()) {
            if (process.getProcessId().size() > 0) {
                for (String p : process.getProcessId()) {
                    MultiValueMap<String, Object> forms = new LinkedMultiValueMap();
                    forms.add("id", p);
                    log.info("forms:" + JSONObject.toJSONString(forms));
                    HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(forms, headers);
                    result1 = restTemplate.postForObject(url, request, String.class);
                    /*String result = restTemplate.postForObject(url, request, String.class);*/
                    System.out.println("result:" + JSONObject.toJSONString(result1));
                    String content = "";
                    if (result1 != null) {
                        System.out.println("删除成功" + "forms" + "/n");
                        System.out.println("result:" + JSONObject.toJSONString(result1));
                    } else {
                        System.out.println("删除失败");
                    }
                }
            }
        }
        return new Resp<>();
    }


}
