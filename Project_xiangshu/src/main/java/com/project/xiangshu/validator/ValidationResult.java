package com.project.xiangshu.validator;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.InitializingBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//存放校验的结果
public class ValidationResult {
    private boolean hasError=false;
    private Map<String,String> errorMsgMap= new HashMap<>();

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }

    //通用方法，将错误信息获取
    public String getErrorMsg(){
      return   StringUtils.join(errorMsgMap.values(),',');
    }
}
