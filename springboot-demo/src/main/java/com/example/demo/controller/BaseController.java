package com.example.demo.controller;

import com.example.demo.entity.Resp;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "基础controller ")
@RestController
@RequestMapping("/springbootTest")
public class BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "测试Swagger")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")})
    @PostMapping("/swagger")
    @ResponseBody
    public Resp testSwagger(String userName, String password){
        Resp resp = new Resp<String>();
        resp.setCode(0);
        resp.setData("String");
        resp.setMessage("成功2");
        resp.setResult(true);
        return resp;
    }


    @ApiOperation(value = "测试Swagger")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")})
    @PostMapping("/addUser")
    @ResponseBody
    public Resp addUser(String userName, String password){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("11");
        userInfo.setPassword("22");
        userInfo.setId(1);
        boolean flag = userInfoService.addUser(userInfo);
        Resp<UserInfo> resp = new Resp<UserInfo>();
        if (flag == true){
            resp.setCode(0);
            resp.setMessage("成功");
            resp.setResult(true);
        }else {
            resp.setCode(1);
            resp.setMessage("失败");
            resp.setResult(true);
        }
        return resp;
    }

}
