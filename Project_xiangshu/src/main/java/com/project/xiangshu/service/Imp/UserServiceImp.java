package com.project.xiangshu.service.Imp;

import com.project.xiangshu.entities.Book;
import com.project.xiangshu.entities.Comment;
import com.project.xiangshu.entities.User;
import com.project.xiangshu.error.BusinessException;
import com.project.xiangshu.error.EmBusinessError;
import com.project.xiangshu.model.servicemodel.bookbasic.BookModel;
import com.project.xiangshu.model.servicemodel.userbasic.UserModel;
import com.project.xiangshu.repository.BookRepository;
import com.project.xiangshu.repository.CommentRepositpry;
import com.project.xiangshu.repository.UserRepository;
import com.project.xiangshu.service.UserService;
import com.project.xiangshu.validator.ValidationResult;
import com.project.xiangshu.validator.ValidatorImp;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ValidatorImp validatorImp;
    @Autowired
    private CommentRepositpry commentRepositpry;
    @Autowired
    private BookRepository bookRepository;
    //注册
     @Override
    public void UserRegist(UserModel model) throws BusinessException {
      //数据校验
        ValidationResult result = validatorImp.validator(model);
        if(result.isHasError()){
         throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrorMsg());
        }
        //model--dataObject
        User user = convertFromUserModel(model);
        //入库
        userRepository.save(user);
    }

    private User convertFromUserModel(UserModel model) {
        if(model==null){
            return null;
        }else {
            User user = new User();
            BeanUtils.copyProperties(model,user);
            return user;
        }
    }
//id查找
    @Override
    public UserModel UserLogin(Integer id) throws BusinessException {
        //登录变为查找
        Optional<User> optional = userRepository.findById(id);

        try{
            optional.get();
        }catch (Exception ex){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        //user--model
        UserModel model = convertFromUser(optional.get());
        return model;
    }

    private UserModel convertFromUser(User user) {
        if(user==null){
            return null;
        }else {
            UserModel model = new UserModel();
            BeanUtils.copyProperties(user,model);
            return model;
        }
    }
//修改
    @Override
    public UserModel UserUpdata(UserModel model) throws BusinessException {
        //数据校验
        ValidationResult result = validatorImp.validator(model);
        if(result.isHasError()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        //model-dataObjject
        User user = convertFromUserModel(model);
        //入库
        userRepository.save(user);
        return model;
    }
//在社区板块的请求中返回部分用户 的基本信息：昵称 头像  账号 个性签名  最近发表的一条评论
    @Override
    public List<UserModel> getCommunityData() {
        //获取用户User 随机取5位
        List<User> listuser = userRepository.findAll();
        //随机生成一个5位的范围
        int count = (int) userRepository.count();
        Random random = new Random();
        if(count<5){
            listuser =listuser.subList(0,count);
        }else {
            int randomInt = random.nextInt(count-5);
            listuser.subList(randomInt,randomInt+5);//随机获取5个数据
        }

        List<UserModel> userModels = new ArrayList<>();
        for(User user :listuser){
            //根据每个用户的id 去获取评论
            List<Comment> comments = new ArrayList<>();
            comments = commentRepositpry.getCommentByUser_id(user.getId());
            //因为id 是自增的，所以最近的评论就是id 最新的评论
            String newComment = getNewComment(comments);
            // dataObject ---model
            UserModel model = convertFromUser(user);
            model.getCommentList().add(newComment);
            userModels.add(model);
        }
        return userModels;
    }
//获取一个用户的详细信息：UserModel 中风封装的所有信息
    @Override
    public UserModel getUserData(Integer id) throws BusinessException {
        //根据用户id 获取User基本信息
        UserModel userModel = UserLogin(id);
        //根据用户id 获取发表的评论
        List<Comment> comments = commentRepositpry.getCommentByUser_id(userModel.getId());
        if(comments.size()>3){
            comments = comments.subList(0,3);
        }
        //获取评论
        List<String> com = new ArrayList<>();
        for(Comment c :comments){
            com.add(c.getComment());
        }

        //根据用户id 获取该用户拥有的书籍
        List<Book> books =  bookRepository.getBooksByUser_id(userModel.getId());
        if(books.size()>10){
            books = books.subList(0,10);
        }
        //dataObject--model
        List<BookModel> bookModels  = new ArrayList<>();
        for(Book book :books){
            BookModel model = new BookModel();
            model = convertFromBook(book);
            bookModels.add(model);
        }
        //拼装成一个完整的User Model
        userModel.setCommentList(com);
        userModel.setBooklist(bookModels);
        return userModel;
    }

    private BookModel convertFromBook(Book book) {
        if(book==null){
            return null;
        }else {
            BookModel model = new BookModel();
            BeanUtils.copyProperties(book,model);
            return model;
        }
    }

    private String getNewComment(List<Comment> comments) {
        //获取最新的评论
        Collections.sort(comments, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                if(o1.getId()>o2.getId()){
                    return 1;
                }else {
                    return -1;
                }
            }
        });
        return comments.get(0).getComment();
    }

    @Override
    public void UserDestroy(Integer id) {
        //操作数据库
        userRepository.deleteById(id);
    }
}
