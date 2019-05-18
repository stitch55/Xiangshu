package com.project.xiangshu.error;
/*
自定义错误的信息
 */
public interface CommonError {
    public int getErrorCode();
    public  String getErrorMsg();
    public CommonError setErrorMsg(String errorMsg);
}
