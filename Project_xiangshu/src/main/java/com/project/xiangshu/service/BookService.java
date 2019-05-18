package com.project.xiangshu.service;

import com.project.xiangshu.error.BusinessException;
import com.project.xiangshu.model.servicemodel.bookbasic.BookModel;

import java.util.List;

//规定书籍的相关操作
public interface BookService {
    //首页返回书籍信息（查找，并按照点赞排序）
    List<BookModel> GetAllBooks() throws BusinessException;
    //添加书籍
    void AddBook(BookModel model) throws BusinessException;
    //根据id 获取书本信息
    BookModel getBookById(Integer id) throws BusinessException;
    //修改书籍
    void UpDataBook(BookModel model) throws BusinessException;
    //删除书籍
    void DeleteBook(Integer id);
}
