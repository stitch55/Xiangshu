package com.project.xiangshu.controller;

import com.project.xiangshu.error.BusinessException;
import com.project.xiangshu.model.servicemodel.bookbasic.BookModel;
import com.project.xiangshu.model.servicemodel.userbasic.UserModel;
import com.project.xiangshu.model.view.BookVO;
import com.project.xiangshu.model.view.UserVO;
import com.project.xiangshu.repository.UserRepository;
import com.project.xiangshu.response.CommonReturnType;
import com.project.xiangshu.service.BookService;
import com.project.xiangshu.service.Imp.UserServiceImp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

//处理首页请求
@Controller
@ResponseBody
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class HomePageController extends BaseController{
    @Autowired
    private BookService bookService;
    @Autowired
   private UserServiceImp userServiceImp;
    //首页请求返回点赞数前的书籍
    @GetMapping("/")
    public CommonReturnType HomePageImag() throws BusinessException {
      List<BookModel> bookmodels = bookService.GetAllBooks();
      //model---View
        List<BookVO> listVo = new ArrayList<>();
        for(BookModel model :bookmodels){
            listVo.add(convertFromBookModel(model));
        }
       return CommonReturnType.create(listVo);
    }

    private BookVO convertFromBookModel(BookModel model) {
        if(model==null){
            return null;
        }else {
            BookVO vo = new BookVO();
            BeanUtils.copyProperties(model,vo);
            return vo;
        }
    }
    //社区请求：返回用户基本信息：头像，昵称，账号，个性签名，最近的一个评论（动态）
    @GetMapping("/community")
    public CommonReturnType getCommunityUser(){
        List<UserModel> userModels = userServiceImp.getCommunityData();
        //model--ViewObject
        List<UserVO> listvo = new ArrayList<>();
        for(UserModel model :userModels){
            listvo.add(convertFromUserModel(model));
        }
        return CommonReturnType.create(listvo);
    }

    private UserVO convertFromUserModel(UserModel model) {
        if(model==null){
            return null;
        }else {
            UserVO vo = new UserVO();
            BeanUtils.copyProperties(model,vo);
            return vo;
        }
    }
}
