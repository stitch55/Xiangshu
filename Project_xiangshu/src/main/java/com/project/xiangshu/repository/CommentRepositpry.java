package com.project.xiangshu.repository;

import com.project.xiangshu.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepositpry extends JpaRepository<Comment,Integer>{
    //根据书的id查找评论
    @Query(value = "select id,comment,book_id,book_name,user_id from tbl_comment where book_id = ?",nativeQuery = true)
    public List<Comment>  getCommentByBook_id(@Param("book_id") Integer id);
    //根据用户的id 查找评论
    @Query(value = "select id,comment,book_id,book_name,user_id from tbl_comment where user_id = ?",nativeQuery = true)
    public List<Comment> getCommentByUser_id(@Param("user_id") Integer id);
}
