package com.project.xiangshu.model.servicemodel;

import com.project.xiangshu.model.servicemodel.userbasic.UserModel;

import java.util.List;

//关注关系的model
public class FocusModel {

    private  Integer id;//数据序列

    private   Integer nowid;//当前用户

    private UserModel userModel;//与当前用户有关系的用户（一对一）

    public Integer getNowid() {
        return nowid;
    }

    public void setNowid(Integer nowid) {
        this.nowid = nowid;
    }



    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }




}
