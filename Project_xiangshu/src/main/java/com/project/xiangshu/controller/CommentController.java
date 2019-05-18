package com.project.xiangshu.controller;

import com.project.xiangshu.model.servicemodel.CommentModel;
import com.project.xiangshu.response.CommonReturnType;
import com.project.xiangshu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class CommentController extends BaseController {
    @Autowired
    private CommentService commentService;
    //写评论，添加评论
    @PostMapping("/comment")
    public CommonReturnType writeComment(@RequestParam("user_id")Integer userId,@RequestParam("book_id")Integer bookId,@RequestParam("/book_name")String bookName,
                                         @RequestParam("comment")String comment){
        //封装数据
        CommentModel model = new CommentModel();
        model.setBook_id(bookId);
        model.setBook_name(bookName);
        model.setComment(comment);
        model.setUser_id(userId);
        //调用Service
        return CommonReturnType.create(null);
    }
}
