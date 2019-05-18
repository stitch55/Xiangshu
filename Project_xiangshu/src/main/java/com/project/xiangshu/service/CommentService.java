package com.project.xiangshu.service;

import com.project.xiangshu.error.BusinessException;
import com.project.xiangshu.model.servicemodel.CommentModel;

//关于对评论的管理
public interface CommentService {
    //对某本书写评论
    void AddComment(CommentModel model) throws BusinessException;
    //评论是没有删除选项的（怎样进行维护后面想到再说）
    //
}
