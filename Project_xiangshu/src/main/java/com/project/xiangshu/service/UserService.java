package com.project.xiangshu.service;

import com.project.xiangshu.error.BusinessException;
import com.project.xiangshu.model.servicemodel.userbasic.UserModel;

import java.util.List;

public interface UserService {
    //注册
    void UserRegist(UserModel model) throws BusinessException;
    //登录
    UserModel UserLogin(Integer id) throws BusinessException;
    //修改
    UserModel UserUpdata(UserModel model) throws BusinessException;
    //获取部分用户信息
    List<UserModel> getCommunityData();
  //获取一个用户的详细信息
    UserModel getUserData(Integer id) throws BusinessException;
    //注销
    void UserDestroy(Integer id);
}
