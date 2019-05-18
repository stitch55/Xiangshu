package com.project.xiangshu.model.view;

import com.project.xiangshu.model.servicemodel.bookbasic.BookModel;
import com.project.xiangshu.model.servicemodel.userbasic.UserModel;

import java.util.ArrayList;
import java.util.List;

public class BookVO {
    private Integer id;//书籍的序号

    private String  book_name; //书籍的名称
    private String  coverUrl; //书籍封面
    private String   author; //作者

    private String  book_source;//提供的书籍的来源，可以为空

    private UserModel ownUser;  //拥有者的信息

    private Integer  type;//书籍的种类;  一共操作两种1 2

    private String  text;//书籍的概要

    private Integer status;//书籍现在的状态:0借出1未借出

    private Integer zanCount;//书籍获得的点赞数
    private List<BookModel> list= new ArrayList<>(); //相似的书籍

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBook_source() {
        return book_source;
    }

    public void setBook_source(String book_source) {
        this.book_source = book_source;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getZanCount() {
        return zanCount;
    }

    public void setZanCount(Integer zanCount) {
        this.zanCount = zanCount;
    }

    public UserModel getOwnUser() {
        return ownUser;
    }

    public void setOwnUser(UserModel ownUser) {
        this.ownUser = ownUser;
    }

    public List<BookModel> getList() {
        return list;
    }

    public void setList(List<BookModel> list) {
        this.list = list;
    }
}
