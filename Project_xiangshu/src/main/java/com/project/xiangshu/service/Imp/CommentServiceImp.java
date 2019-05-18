package com.project.xiangshu.service.Imp;

import com.project.xiangshu.entities.Comment;
import com.project.xiangshu.error.BusinessException;
import com.project.xiangshu.error.EmBusinessError;
import com.project.xiangshu.model.servicemodel.CommentModel;
import com.project.xiangshu.repository.CommentRepositpry;
import com.project.xiangshu.service.CommentService;
import com.project.xiangshu.validator.ValidationResult;
import com.project.xiangshu.validator.ValidatorImp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    private ValidatorImp validatorImp;
    @Autowired
    private CommentRepositpry commentRepositpry;
    //写评论
    @Override
    public void AddComment(CommentModel model) throws BusinessException {
        //数据校验
        ValidationResult result = new ValidationResult();
        result = validatorImp.validator(model);
        if(result.isHasError()){
            throw  new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        //mode-dataObject
        Comment comment = new Comment();
        comment = convertFromComModel(model);
        //入库
        commentRepositpry.save(comment);
    }

    private Comment convertFromComModel(CommentModel model) {
        if(model==null){
            return null;
        }else {
            Comment com = new Comment();
            BeanUtils.copyProperties(model,com);
            return com;
        }
    }
}
