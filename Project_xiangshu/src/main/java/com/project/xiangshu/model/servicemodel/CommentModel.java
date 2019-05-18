package com.project.xiangshu.model.servicemodel;

import javax.validation.constraints.NotNull;

public class CommentModel {
    private Integer id;  //评论序号
    @NotNull(message = "编写评论者信息不能为空")
    private Integer user_id;//那个用户写的评论
    @NotNull(message = "评论不能为空")
    private String comment;//评论内容
    @NotNull(message = "评论对象不能为空")
    private Integer book_id; //对那本书的评论

    private String book_name;//

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
}
