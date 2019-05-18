package com.project.xiangshu.model.view;

import com.project.xiangshu.model.servicemodel.bookbasic.BookModel;

import java.util.ArrayList;
import java.util.List;

public class UserVO {
    private  Integer id;  //自增主键

    private String nickname; //昵称

    private String inspiration;//感悟

    private String photoUrl;//头像

    private Integer own_count;//拥有的数量

    private Integer borrow_count;//借阅的数量
    //发表的评论
    private List<String> commentList= new ArrayList<>();
    //拥有的书籍
    private List<BookModel> booklist= new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getInspiration() {
        return inspiration;
    }

    public void setInspiration(String inspiration) {
        this.inspiration = inspiration;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Integer getOwn_count() {
        return own_count;
    }

    public void setOwn_count(Integer own_count) {
        this.own_count = own_count;
    }

    public Integer getBorrow_count() {
        return borrow_count;
    }

    public void setBorrow_count(Integer borrow_count) {
        this.borrow_count = borrow_count;
    }

    public List<String> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<String> commentList) {
        this.commentList = commentList;
    }

    public List<BookModel> getBooklist() {
        return booklist;
    }

    public void setBooklist(List<BookModel> booklist) {
        this.booklist = booklist;
    }
}
