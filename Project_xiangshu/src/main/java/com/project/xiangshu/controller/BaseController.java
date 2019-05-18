package com.project.xiangshu.controller;

import com.project.xiangshu.error.BusinessException;
import com.project.xiangshu.error.EmBusinessError;
import com.project.xiangshu.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//将处理异常的方式写在基本Controller中
//处理Controller 抛出的异常
public class BaseController {
    @ExceptionHandler(BusinessException.class) //对Controller抛出的那个异常进行处理
    @ResponseStatus(HttpStatus.OK) //返回状码200
    @ResponseBody
    public Object handlerException(HttpServletRequest request,Exception ex){
        Map<String,Object> responseData= new HashMap<String,Object>();//errCode  errMsg
        if(ex instanceof BusinessException){
            BusinessException businessException = (BusinessException)ex;
            responseData.put("errCode",businessException.getErrorCode());  //获取封装在ex 中的自定义异常
            responseData.put("errMsg",businessException.getErrorMsg());
        }else {
            //未知错误
            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrorCode());
            responseData.put("errMsg",EmBusinessError.UNKNOWN_ERROR.getErrorMsg());

        }
        return CommonReturnType.create(responseData,"fail");
    }
}
