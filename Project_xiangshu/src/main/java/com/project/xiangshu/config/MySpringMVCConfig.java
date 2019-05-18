package com.project.xiangshu.config;

import com.project.xiangshu.compnent.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//进行SpringMvc 的相关配置
@Configuration
public class MySpringMVCConfig extends WebMvcConfigurerAdapter {
 //   @Bean   //数据交互之后再进行拦截设置
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        return new WebMvcConfigurerAdapter() {
            //设置拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/login","/user/register");
            }
        };
    }
}
