package com.project.xiangshu.response;

/**
 * 归一化返回的数据内容
 */
public class CommonReturnType {
    /*
    /status : 表明对应请求处理的结果，"success" 或者"fail"
    在处理请求的时候，后端会向前端返回处理的状态码
     */
    private String status;
    /*
    status = "seccess" 返回前端需要的数据
    status = "fail"  data 内使用通用的错误码格式
     */
    private  Object data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    //定义通用的创建方式
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result,"success");
    }
    public static CommonReturnType create(Object result, String status){
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return type;
    }
}
