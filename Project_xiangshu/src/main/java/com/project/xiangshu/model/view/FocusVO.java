package com.project.xiangshu.model.view;

import com.project.xiangshu.model.servicemodel.userbasic.UserModel;

public class FocusVO {
    private  Integer id;//数据序列

    private   Integer nowid;//当前用户

    private UserVO userVO;//与当前用户有关系的用户（一对一）

    public Integer getNowid() {
        return nowid;
    }

    public void setNowid(Integer nowid) {
        this.nowid = nowid;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
