package com.project.xiangshu.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

//获取Validator
@Component
public class ValidatorImp implements InitializingBean{
    private Validator validator;

    //定义校验的方法
    public ValidationResult validator(Object bean){
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<Object>> set = validator.validate(bean);
        if(set.size()>0){
            result.setHasError(true);
            set.forEach(n->{
                String errorMsg = n.getMessage();
                String errorProperty = n.getPropertyPath().toString();
                result.getErrorMsgMap().put(errorProperty,errorMsg);
            });
        }
        return result;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        //获取校验器的实例
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
