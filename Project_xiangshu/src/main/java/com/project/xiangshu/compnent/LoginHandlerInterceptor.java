package com.project.xiangshu.compnent;

import com.project.xiangshu.error.BusinessException;
import com.project.xiangshu.error.EmBusinessError;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//做登陆拦截
public class LoginHandlerInterceptor implements HandlerInterceptor {
    //在Controller处理之前

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //检查session 是否有
        String user =(String) request.getSession().getAttribute("LoginUser");
        if(user==null){
            throw  new BusinessException(EmBusinessError.PLEASE_LOGIN_FIRST);
        }
        return true;
    }
}
