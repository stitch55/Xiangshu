package com.project.xiangshu.repository;

import com.project.xiangshu.entities.Book_cover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Book_coverRepository extends JpaRepository<Book_cover,Integer> {
    //根据书本的名字获取数据对象
    @Query(value = "select id,book_name,cover_url from tbl_bookcover where book_name=?",nativeQuery = true)
    Book_cover getCoverByName(@Param("book_name")String name);
}
