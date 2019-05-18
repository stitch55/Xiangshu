package com.project.xiangshu.model.servicemodel.userbasic;

import com.project.xiangshu.model.servicemodel.bookbasic.BookModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

//用户基本的增删改查需要的Service模型
public class UserModel {

    private  Integer id;  //自增主键
    @NotNull (message = "昵称不能为空")
    private String nickname; //昵称
    @NotNull(message = "账号不能为空")
    private String number;//账号
    @NotNull(message = "密码不能为空")
    private String password;//密码
    @NotNull(message = "签名不能为空")
    private String inspiration;//感悟
    @NotNull(message = "头像不能为空")
    private String photoUrl;//头像
    @Min(value = 0,message = "数量不能小于0")
    private Integer own_count;//拥有的数量
    @Min(value = 0,message = "数量不能小于0")
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
