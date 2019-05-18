package com.project.xiangshu.repository;

import com.project.xiangshu.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    //查找同名书籍
    @Query(value = "select id,author,book_name,book_source,status,text,user_id,zan_count,type from tbl_book where book_name =?",nativeQuery = true)
    public List<Book>  getBooksByBookName(@Param("book_name") String name);
    //根据user_id 查找书本
    @Query(value = "select id,author,book_name,book_source,status,text,zan_count,type from tbl_book where user_id =?",nativeQuery = true)
    public List<Book> getBooksByUser_id(@Param("user_id") Integer id);
}
