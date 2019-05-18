package com.project.xiangshu.controller;

import com.project.xiangshu.error.BusinessException;
import com.project.xiangshu.error.EmBusinessError;
import com.project.xiangshu.model.servicemodel.userbasic.UserModel;
import com.project.xiangshu.model.view.UserVO;
import com.project.xiangshu.response.CommonReturnType;
import com.project.xiangshu.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/user")
@CrossOrigin(allowedHeaders = "*",allowCredentials = "true")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest httpServletRequest;
    //注册
    @PostMapping("/register")
    public CommonReturnType Register(@RequestBody UserModel model) throws BusinessException {//前端数据的传递方式必须是：application/json

//       @RequestParam("nickname") String name, @RequestParam("number")String number,@RequestParam("password") String password,@RequestParam("inspiration") String inspiration,
//      @RequestParam("photoUrl") String photoUrl,@RequestParam("own_count") Integer ownCount,@RequestParam("borrow_count") Integer borrCount //前端数据传递的方式：
        //封装参数,调用service
//        UserModel model = new UserModel();
//        model.setNickname(name);
//        model.setNumber(number);
//        model.setInspiration(inspiration);
//        model.setPassword(password);
//        model.setPhotoUrl(photoUrl);
//        model.setOwn_count(ownCount);
//        model.setBorrow_count(borrCount);
System.out.println("OK");
        userService.UserRegist(model);
        return CommonReturnType.create(null);
    }

    //登录
    @GetMapping("/login")
    public CommonReturnType Login(@RequestParam("id") Integer id) throws BusinessException {//模拟第三方登录之登陆三个
        System.out.println("OK");
        //相当于查找
        UserModel model = userService.UserLogin(id);
        if(model==null){
            throw  new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        //将用户信息保存在session 中
        httpServletRequest.getSession().setAttribute("LoginUser",model.getNickname());
        //model---ViewObjectl
        UserVO vo = converFromModel(model);

        return CommonReturnType.create(vo);
    }

    private UserVO converFromModel(UserModel model) {
        if(model==null){
            return null;
        }else {
            UserVO vo = new UserVO();
            BeanUtils.copyProperties(model,vo);
            return vo;
        }
    }

    //修改
    //先返回当前用户的信息
     @GetMapping("/data/{id}")
    public ModelAndView getUserData(@PathVariable("id") Integer id){
        //重定向到登录获取用户的基本数据
        String url = "redirect:/user/login?id="+id;

        return new ModelAndView(url);
     }
    //修改
    @PostMapping("/updata")
    public CommonReturnType UserDataUpdata(@RequestBody UserModel model) throws BusinessException {
 //       @RequestParam("nickname") String name, @RequestParam("number")String number,@RequestParam("password") String password,@RequestParam("inspiration") String inspiration
 //           ,@RequestParam("photoUrl") String photoUrl,@RequestParam("own_count") Integer ownCount,@RequestParam("borrow_count") Integer borrCount
        //封装参数,调用service
//        UserModel model = new UserModel();
//        model.setNickname(name);
//        model.setNumber(number);
//        model.setInspiration(inspiration);
//        model.setPassword(password);
//        model.setPhotoUrl(photoUrl);
//        model.setBorrow_count(borrCount);
//        model.setOwn_count(ownCount);

         UserVO vo =converFromModel(userService.UserUpdata(model)) ;
        return CommonReturnType.create(vo);
    }
    //获取一个用户的详细信息

    //注销
    @GetMapping("/delete")
    public CommonReturnType UserDelete(@RequestParam("id") Integer id){
        //调用service
        userService.UserDestroy(id);

        return CommonReturnType.create(null);
    }
}
