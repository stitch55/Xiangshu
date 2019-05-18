package com.project.xiangshu.model.servicemodel.bookbasic;

import com.project.xiangshu.model.servicemodel.CommentModel;
import com.project.xiangshu.model.servicemodel.userbasic.UserModel;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

//基本的增删改查需要的service 数据模型
public class BookModel {

    private Integer id;//书籍的序号
    @NotNull(message = "书名不能为空")
    private String  book_name; //书籍的名称
    @NotNull(message = "作者不能为空")
    private String   author; //作者
    @NotNull(message = "书籍封面不能为空")
    private String  coverUrl;
    private String  book_source;//提供的书籍的来源，可以为空

    private UserModel ownUser;  //拥有者的信息  //需要查询其他的表user_id--User
    @NotNull
    private Integer  type;//书籍的种类;  一共操作两种1 2

    private String  text;//书籍的概要
    @NotNull
    private Integer status;//书籍现在的状态:0借出1未借出
    @NotNull
    private Integer zanCount;//书籍获得的点赞数
   //同名的书籍；这里的书籍信息只需要书籍基本的信息（名字，作者，封面）
    private List<BookModel> listbook= new ArrayList<>();  //需要查询其他的表 name--book
   //网站中对这本书的评论
    private List<CommentModel> comment = new ArrayList<>();//需要查询其他的表  book_id ---Comment

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

    public List<BookModel> getListbook() {
        return listbook;
    }

    public void setListbook(List<BookModel> listbook) {
        this.listbook = listbook;
    }

    public List<CommentModel> getComment() {
        return comment;
    }

    public void setComment(List<CommentModel> comment) {
        this.comment = comment;
    }
}
