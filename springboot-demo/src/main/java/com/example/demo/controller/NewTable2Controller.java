package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.*;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
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
@Slf4j//日志打印


//完全写死，调用一个接口
public class NewTable2Controller {

    private  String token = "";

    /*private final String token1 = "JSESSIONID=35F9F7A102FF0D0C00BA7BD0D16172C1";*/

    @PostMapping("/create2")//映射一个POST请求
                                /*Spring MVC新特性
                                提供了对Restful风格的支持
                                @GetMapping，处理get请求
                                @PostMapping，处理post请求
                                @PutMapping，处理put请求
                                @DeleteMapping，处理delete请求*/


    public Resp<Void> create(@RequestBody ProcessTest processTest) throws NoSuchAlgorithmException {

        CreateDto createDto1 = CreateDto.builder()
                //.projectId("sjcas000001")
                .projectId("sjcas000022")
                .parentNodeId(99)
                .sceneType(3)
                .nodeType(11)
                .nodeName("test_heshiqi0185555")
                .schemaId(60335)
                .build();
        RestTemplate restTemplate = new RestTemplate();
        token = processTest.getToken();
        String url = "http://10.251.129.24/data-rd-hub/api/common/node/create";//调用接口地址
        /*String url = "http://bigdata.test.yonghui.cn/data-rd-hub/api/common/node/create";*/
        HttpHeaders headers = new HttpHeaders();  //调用万维网http协议，能够实现接口调用的http请求
        //定义请求参数类型，这里用json所以是MediaType.APPLICATION_JSON
        headers.setContentType(MediaType.APPLICATION_JSON);//post提交数据
        headers.add("orgCode", "sjcas000001");//添加组织组织ID
        headers.add("token", token);//引用全局token
        HttpEntity<CreateDto> request = new HttpEntity<>(createDto1, headers);
        JSONObject result = restTemplate.postForObject(url, request, JSONObject.class);
        log.info("create result:" + result);//日志输出
        //nodeID随时变化，需要实时修改
        Integer nodeId = null;
        if (result.containsKey("data")) {
            nodeId = result.getInteger("data");
        }
        SecureRandom random1 = SecureRandom.getInstance("SHA1PRNG");//生成随机数


        String randomm =Integer.toString(random1.nextInt());

        if (randomm.contains("-")){
            randomm =randomm.substring(1,randomm.length());
        }

        //获取当前时间
        Date dNow = new Date();
        //输出时间格式
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        // newSubmitTable
        MetadataRequestDTO metadataRequestDTO = MetadataRequestDTO.builder()//引用MetadataRequestDTO实体类
                .schemaId(206)
                .topicId(99)
                .enName("testtable" +randomm
                                /*随机数*//*random1.nextInt()*/
                        /*ft.format(dNow)*/)//表名，不可重复，需要修改  mid_xtt_ceshi_000000000000000000
                .cnName("bigdata")//中文名
                .tableType(1)
                .remark("bigdata")//描述
                .cycleDate(30)
                .assestsLevel("A1")
                .storeFileFormat("SEQUENCEFILE")
                //.projectId("sjcas000001")
                .projectId("sjcas000022")
                .dWLayer(false)
                .layerType(1)
                .layerTypeText("贴源数据层")
                .schemaName("stg")
                .sourceSchemaId(205)
                .erjiName("未知主题域")
                .nodeId(nodeId)
                .build();//创建

        List<ColSchemaRequestDTOList> list = new ArrayList<>();//创建list集合
        ColSchemaRequestDTOList colSchemaRequestDTOList = ColSchemaRequestDTOList.builder()//引用实体类colSchemaRequestDTOList
                .index("1fj8odh97_4efhrxaBXYpBpJnp_cpnkhsvurog")
                .remark("T")
                .colEnName("asd98")
                .colCnName("T")
                .colLen("")
                .colType("10")
                .pkType(2)
                .colZoneType(2)
                .metaStatus(0)
                .sortNum(0)
                .build();//创建
        list.add(colSchemaRequestDTOList);
        JsonRootBean jsonRootBean1 = JsonRootBean.builder()
                .colSchemaRequestDTOList(list)
                .metadataRequestDTO(metadataRequestDTO)
                .build();
        newSubmitTable(jsonRootBean1,processTest);

        return new Resp<>();
    }


    public Resp<Void> newSubmitTable(@RequestBody JsonRootBean jsonRootBean ,ProcessTest processTest) {//@ResponseBody的作用是将java对象转为json格式的数据

        RestTemplate restTemplate = new RestTemplate();
        //获取插入数据接口
        String url = "http://10.251.129.24/data-rd-hub/api/table/newSubmitTable";
        HttpHeaders headers = new HttpHeaders();
        //定义请求参数类型，这里用json所以是MediaType.APPLICATION_JSON
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("orgCode", "sjcas000001");
        headers.add("token", token);//引用公共token

        log.info("jsonRootBean:" + JSON.toJSONString(jsonRootBean));
        HttpEntity<JsonRootBean> request = new HttpEntity<>(jsonRootBean, headers);
        String result = restTemplate.postForObject(url, request, String.class);
        log.info("newSubmitTable result:" + JSON.toJSONString(result));//日志打印

        List<Integer> envTypes = new ArrayList<>();
        envTypes.add(1);
        envTypes.add(2);
        List<Integer> moreMetaStatus = new ArrayList<>();
        moreMetaStatus.add(0);
        moreMetaStatus.add(1);
        moreMetaStatus.add(2);
        moreMetaStatus.add(-1);
        EngineRequest engineRequest = EngineRequest.builder()
                .engineType(1)
                .envTypes(envTypes)
                .moreMetaStatus(moreMetaStatus)
                .pageIndex(1)
                .pageSize(10)
                .projectId("sjcas000001")
                .totalCount(0)
                .build();

        getList(engineRequest,processTest);
        return new Resp<>();
    }

    public Resp<Void> getList(@RequestBody EngineRequest engineRequest, ProcessTest processTest) {//@ResponseBody的作用是将java对象转为json格式的数据

        RestTemplate restTemplate = new RestTemplate();
        //获取插入数据接口
        String url = "http://10.251.129.24/data-rd-hub/api/table/queryPageTableMetaDataList";
        HttpHeaders headers = new HttpHeaders();
        //定义请求参数类型，这里用json所以是MediaType.APPLICATION_JSON
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("orgCode", "sjcas000001");
        headers.add("token", token);//引用公共token

        log.info("jsonRootBean:" + JSON.toJSONString(engineRequest));
        HttpEntity<EngineRequest> request = new HttpEntity<>(engineRequest, headers);
      /*  String result = restTemplate.postForObject(url, request, String.class);
        log.info("getList result:" + JSON.toJSONString(result));//日志打印*/

        JSONObject result = restTemplate.postForObject(url, request, JSONObject.class);
        log.info("create result:" + result);//日志输出

        JSONObject jsonObject = null;
        String id = null;
        if (result.containsKey("data")) {
            jsonObject = result.getJSONObject("data");
            if (null != jsonObject){
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                log.info("jsonArray:" + jsonArray);//日志输出
                Object list0 = jsonArray.get(0);
                log.info("list0:" + list0);//日志输出
                LinkedHashMap result2  = (LinkedHashMap)list0;
                if (result2.containsKey("id")){
                    id = result2.get("id").toString();

                    ProdRequest prodRequest = ProdRequest.builder()
                            .detailUrl("metadata/#/data_details/"+id)
                            .metadataId(Integer.parseInt(id))
                            .orgCode("sjcas000001")
                            .build();

                    String url2 = "http://10.251.129.24/data-rd-hub/api/table/newSubmitTableProd";
                    HttpHeaders headers1 = new HttpHeaders();
                    //定义请求参数类型，这里用json所以是MediaType.APPLICATION_JSON
                    headers1.setContentType(MediaType.APPLICATION_JSON);
                    headers1.add("orgCode", "sjcas000001");
                    headers1.add("token", token);//引用公共token

                    log.info("jsonRootBean:" + JSON.toJSONString(prodRequest));
                    HttpEntity<ProdRequest> requestProdRequest = new HttpEntity<>(prodRequest, headers1);

                    JSONObject Prodresult = restTemplate.postForObject(url2, requestProdRequest, JSONObject.class);

                    log.info("Prodresult:" + JSON.toJSONString(Prodresult));

                    if (result.containsKey("code")) {
                        if ( result.getJSONObject("data").toString().equals("0000000")) {


                            String url3 = "http://10.251.129.24/approval/approval/center/process?currentPage=1&pageSize=10&organizationCode=sjcas000001&submitter=80900867";
                            HttpHeaders headers3 = new HttpHeaders();
                            //定义请求参数类型，这里用json所以是MediaType.APPLICATION_JSON
                            headers3.setContentType(MediaType.APPLICATION_JSON);
                            headers3.add("orgCode", "sjcas000001");
                            headers3.add("token", token);//引用公共token

                            log.info("jsonRootBean:" + JSON.toJSONString(prodRequest));
                            HttpEntity requestRequest = new HttpEntity(headers3);

                            JSONObject result4 = restTemplate.postForObject(url3, requestRequest, JSONObject.class);
                            JSONObject jsonObject1 = null;
                            String resultId;
                            if (result4.containsKey("data")) {
                                jsonObject1 = result4.getJSONObject("data");
                                log.info("jsonObject1" + JSON.toJSONString(jsonObject1));
                                JSONArray jsonArray1 = jsonObject1.getJSONArray("item");
                                log.info("jsonArray" + JSON.toJSONString(jsonArray1));
                                Object list1 = jsonArray1.get(0);
                                LinkedHashMap linkedHashMap = (LinkedHashMap) list1;
                                if (linkedHashMap.containsKey("id")) {
                                    resultId = linkedHashMap.get("id").toString();
                                    if (!StringUtils.isEmpty(resultId)){
                                        processTest.setId(Integer.parseInt(resultId));

                                        String processUrl = "http://10.251.129.24:8088/approval/center/approval/process";
                                        HttpHeaders processHeaders = new HttpHeaders();
                                        //定义请求参数类型，这里用json所以是MediaType.APPLICATION_JSON
                                        processHeaders.setContentType(MediaType.APPLICATION_JSON);
                                        processHeaders.add("orgCode", "sjcas000001");
                                        processHeaders.add("token", processTest.getToken());//引用公共token
                                        processHeaders.add("usercode","80900867");

                                        log.info("processTest:" + JSON.toJSONString(processTest));
                                        HttpEntity<ProcessTest> processRequest = new HttpEntity<>(processTest, processHeaders);

                                        JSONObject processResult = restTemplate.postForObject(processUrl, processRequest, JSONObject.class);

                                        log.info("processResult:" + JSON.toJSONString(processResult));
                                    }
                                }

                            }
                        }
                    }

                }
            }
        }

        JSON.toJSONString(result);
        return new Resp<>();
    }


    @PostMapping("/process")//映射一个POST请求
                                /*Spring MVC新特性
                                提供了对Restful风格的支持
                                @GetMapping，处理get请求
                                @PostMapping，处理post请求
                                @PutMapping，处理put请求
                                @DeleteMapping，处理delete请求*/


    public Resp<Void> process(@RequestBody ProcessTest processTest)  {

        RestTemplate restTemplate = new RestTemplate();
        String url2 = "http://10.251.129.24:8088/approval/center/approval/process";
        HttpHeaders headers1 = new HttpHeaders();
        //定义请求参数类型，这里用json所以是MediaType.APPLICATION_JSON
        headers1.setContentType(MediaType.APPLICATION_JSON);
        headers1.add("orgCode", "sjcas000001");
        headers1.add("token", processTest.getToken());//引用公共token
        headers1.add("usercode","80900867");

        log.info("processTest:" + JSON.toJSONString(processTest));
        HttpEntity<ProcessTest> requestProdRequest = new HttpEntity<>(processTest, headers1);

        JSONObject result = restTemplate.postForObject(url2, requestProdRequest, JSONObject.class);

        log.info("result:" + JSON.toJSONString(result));
        return new Resp<>();
    }

}
