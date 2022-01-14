package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class XxlDto {
    public String  jobGroup;//执行器
    public String jobDesc;//任务描述
    public String author;//负责人
    public String alarmEmail;//报警邮件
    public String scheduleType;
    public String scheduleConf;
    public String cronGen_display;
    public String schedule_conf_Cron;
    /*public String schedule_conf_FIX_RATE;*/
    public String glueType;
    public String executorHandler;
    public String executorParam;
    public String executorRouteStrategy;//路由策略
    public String childJobId;//子任务ID
    public String misfireStrategy;//调度过期策略
    public String executorBlockStrategy;//阻塞处理策略
    public String executorTimeout;//任务超时时间
    public String executorFailRetryCount;//失败重试次数
    public String glueRemark;
}
