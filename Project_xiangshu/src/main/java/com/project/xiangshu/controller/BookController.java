package com.project.xiangshu.controller;

import com.project.xiangshu.error.BusinessException;
import com.project.xiangshu.error.EmBusinessError;
import com.project.xiangshu.model.servicemodel.bookbasic.BookModel;
import com.project.xiangshu.model.servicemodel.userbasic.UserModel;
import com.project.xiangshu.model.view.BookVO;
import com.project.xiangshu.response.CommonReturnType;
import com.project.xiangshu.service.BookService;
import com.project.xiangshu.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/book")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class BookController extends BaseController {
   @Autowired
   private BookService bookService;
    /**
     * 图书的添加
     */
    @PostMapping("/add")
    public CommonReturnType addBook(@RequestParam("book_name")String bookName,@RequestParam("author")String author,@RequestParam("book_source")String source,
   @RequestParam("text")String text,@RequestParam("user_id")Integer user_id,@RequestParam("type")Integer type) throws BusinessException {
        //拼装数据
        BookModel bookModel = new BookModel();
        bookModel.setBook_name(bookName);
        bookModel.setAuthor(author);
        bookModel.setText(text);
        bookModel.setType(type);
        UserModel userModel = new UserModel();
        userModel.setId(user_id);
        bookModel.setOwnUser(userModel);
        bookModel.setStatus(0);
        bookModel.setZanCount(0);
        //调用Service
        bookService.AddBook(bookModel);
        return CommonReturnType.create(null);
    }

    //修改书籍信息
    //获取要修改的书本的信息
    @GetMapping("/getData")
    public CommonReturnType getBookData(@RequestParam("id")Integer id) throws BusinessException {
        //调用调用Service获取该用户的信息
         BookModel model = bookService.getBookById(id);
         if(model==null){
             throw  new BusinessException(EmBusinessError.USER_NOT_EXIST);
         }
         //model--view
        BookVO vo  = convertFromBookModel(model);
        return CommonReturnType.create(vo);
    }
    @PutMapping("/updata")
    public CommonReturnType EditBook(@RequestParam("author")String author,@RequestParam("book_name")String bookName,@RequestParam("text")String text,
                                     @RequestParam("book_source")String source) throws BusinessException {
        //拼装数据,书本信息的修改（基本信息：名字，作者，概要，获取资源的方式）
        BookModel model = new BookModel();
        model.setAuthor(author);
        model.setText(text);
        model.setBook_name(bookName);
        model.setBook_source(source);
        //调用Service
        bookService.UpDataBook(model);
        return CommonReturnType.create(null);
    }
    private BookVO convertFromBookModel(BookModel model) {
        BookVO vo = new BookVO();
        BeanUtils.copyProperties(model,vo);
        return vo;
    }

    //删除书籍
    @DeleteMapping("/delete")
    public CommonReturnType DaleteBook(@RequestParam("id")Integer id){
        bookService.DeleteBook(id);
        return CommonReturnType.create(null);
    }

}
