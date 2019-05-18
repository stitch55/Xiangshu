package com.project.xiangshu.service.Imp;

import com.project.xiangshu.Constant;
import com.project.xiangshu.entities.Book;
import com.project.xiangshu.entities.Book_cover;
import com.project.xiangshu.entities.Comment;
import com.project.xiangshu.entities.User;
import com.project.xiangshu.error.BusinessException;
import com.project.xiangshu.error.EmBusinessError;
import com.project.xiangshu.model.servicemodel.CommentModel;
import com.project.xiangshu.model.servicemodel.bookbasic.BookModel;
import com.project.xiangshu.model.servicemodel.userbasic.UserModel;
import com.project.xiangshu.repository.BookRepository;
import com.project.xiangshu.repository.Book_coverRepository;
import com.project.xiangshu.repository.CommentRepositpry;
import com.project.xiangshu.repository.UserRepository;
import com.project.xiangshu.service.BookService;
import com.project.xiangshu.validator.ValidationResult;
import com.project.xiangshu.validator.ValidatorImp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class BookServiceImp implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepositpry commentRepositpry;
    @Autowired
    private ValidatorImp validatorImp;
    @Autowired
    private Book_coverRepository bookCoverRepository;
    @Override
    @Transactional //事务
    public List<BookModel> GetAllBooks() throws BusinessException {
        //在tbl_book 表中查询所有的书籍，点赞数排序取前3
        List<Book> list = bookRepository.findAll();
        List<Book>lists = Sort(list);
       List<BookModel> BookModels = new ArrayList<>();
        //16本书相关信息
        for(Book b:lists) {
            //根据书本拥有着id ---tbl-User  获取拥有者信息
            Optional<User> user = userRepository.findById(b.getUser_id());
            User own_user;
            try{
               own_user = user.get();
            }catch (Exception ex){
                own_user = null;
            }

            //根据书名 再次在tbl-book 中查找 同名书籍
            List<Book> name_book = bookRepository.getBooksByBookName(b.getBook_name());
            //根据 书籍的id 在tbl-comment 中查找 对该本书的所有评论
            List<Comment> comments = commentRepositpry.getCommentByBook_id(b.getId());
            //dao---model
            BookModel model =  convertFromBook(b);
            UserModel userModel = convertFromUser(own_user);
            List<BookModel> name_models = new ArrayList<>();
            for(Book book:name_book){
                name_models.add(convertFromBook(book));
            }
            List<CommentModel> commentModels = new ArrayList<>();
            for(Comment com:comments){
                commentModels.add(convertFromComment(com));
            }
            //将所有的信息封装到bookModel 中
            model.setOwnUser(userModel);
            model.setListbook(name_models);
            model.setComment(commentModels);
            BookModels.add(model);
        }
        return BookModels;
    }

    private CommentModel convertFromComment(Comment com) throws BusinessException {
        CommentModel model =new  CommentModel();
        if(com==null){
            throw new BusinessException(EmBusinessError.COMMENT_NOT_EXIST);
        }else {
            BeanUtils.copyProperties(com,model);
        }
        return model;
    }

    private UserModel convertFromUser(User own_user) throws BusinessException {
        //user--model
        UserModel userModel = new UserModel();
        if(own_user==null){
           throw new BusinessException(EmBusinessError.USER_NOT_EXIST.setErrorMsg("该书本拥有者查找失败"));
        }else {
            BeanUtils.copyProperties(own_user,userModel);
        }
        return userModel;
    }

    private BookModel convertFromBook(Book book) throws BusinessException {
        if(book==null){
            throw new BusinessException(EmBusinessError.BOOK_NOT_EXIST);
        }else {
            BookModel model = new BookModel();
            BeanUtils.copyProperties(book,model);
            return model;
        }
    }

    private List<Book> Sort(List<Book> list) {
        //根据点赞排序并且只选前三
        Collections.sort(list, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                if(o1.getZanCount()>o2.getZanCount()){
                    return  1;
                }else if(o1.getZanCount()<o2.getZanCount()){
                    return -1;
                }else {
                    return 0;
                }
            }
        });
        //只选前三个元素
        List<Book> books = list.subList(0, Constant.BOOK_COUNT);
        return books;
    }

 //添加书本
    @Override
    public void AddBook(BookModel model) throws BusinessException {
    //数据校验
        ValidationResult result = new ValidationResult();
        result = validatorImp.validator(model);
        if(result.isHasError()){
            throw  new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrorMsg());
        }
        //书籍封面的获取，书籍的名字在封面库中搜索
        Book_cover cover = bookCoverRepository.getCoverByName(model.getBook_name());
        model.setCoverUrl(cover.getCover());
        //model---dataobject
        Book book = new Book();
        book = convertFromBookModel(model);
        //入库
        bookRepository.save(book);
    }
//根据id 获取书本的信息：用于书本信息的修改（基本信息：名字，作者，概要，获取资源的方式）
    @Override
    public BookModel getBookById(Integer id) throws BusinessException {
        Optional<Book> Pbook = bookRepository.findById(id);
        Book book = new Book();
        try{
            book = Pbook.get();
        }catch (Exception ex){
            book = null;
        }

        //dataObject --model
        BookModel model = convertFromBook(book);

        return model;
    }

    //BookModel --Book
    private Book convertFromBookModel(BookModel model) {
        if(model==null){
            return null;
        }else {
            Book book = new Book();
            BeanUtils.copyProperties(model,book);
            return book;
        }
    }

//修改书本信息
    @Override
    public void UpDataBook(BookModel model) throws BusinessException {
        //校验数据
        ValidationResult result = new ValidationResult();
        result = validatorImp.validator(model);
        if(result.isHasError()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrorMsg());
        }
        //model--dataObject
        Book book = convertFromBookModel(model);

        //入库
        bookRepository.save(book);

    }
//删除信息
    @Override
    public void DeleteBook(Integer id) {
        bookRepository.deleteById(id);
    }
}
