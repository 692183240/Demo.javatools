package com.example.demo.service;

import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements UserInfoServiceImpl{

    @Autowired
    private UserInfoMapper userInfoMapper;

    //新增用户
    @Override
    public boolean addUser(UserInfo userInfo) {
        int flag = userInfoMapper.insert(userInfo);
        if (flag >= 1){
            return true;
        }else {
            return false;
        }
    }


}
