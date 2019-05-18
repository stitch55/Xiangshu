package com.project.xiangshu.service;


import com.project.xiangshu.error.BusinessException;
import com.project.xiangshu.model.servicemodel.FocusModel;
import com.project.xiangshu.model.servicemodel.userbasic.UserModel;

import java.util.List;

public interface FocusService {
    //添加关注关系
    void addFocus(FocusModel focusModel) throws BusinessException;
    //获取当前用户关注的用户信息
    List<FocusModel>  getFocusUsers(Integer id) throws BusinessException;
    //获取所有关注了当前用户的用户基本信息
    List<FocusModel> getFocusedUsers(Integer id) throws BusinessException;
    //解除关注关系
   void relieveFocus(Integer id);
}
