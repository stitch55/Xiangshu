package com.project.xiangshu.controller;


import com.project.xiangshu.error.BusinessException;
import com.project.xiangshu.error.EmBusinessError;
import com.project.xiangshu.model.servicemodel.FocusModel;
import com.project.xiangshu.model.servicemodel.userbasic.UserModel;
import com.project.xiangshu.model.view.FocusVO;
import com.project.xiangshu.model.view.UserVO;
import com.project.xiangshu.response.CommonReturnType;
import com.project.xiangshu.service.FocusService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
//对于网站中所有关注关系的管理
@Controller
@RequestMapping("/focus")
@ResponseBody
@CrossOrigin(allowedHeaders = "*",allowCredentials = "true")
public class FocusController extends BaseController {
    @Autowired
    private FocusService focusService;

    //获取当前用户关注了的用户---关注了谁
     @GetMapping("/getfocus")
    public CommonReturnType getFocus(@RequestParam("id")Integer id) throws BusinessException {
         //根据用户的id查询用户关注了的用户
         List<FocusModel> focusModels = focusService.getFocusUsers(id);

         //usermodel---view
         List<FocusVO> focusVOList =convertFromFocusModels(focusModels);

         return CommonReturnType.create(focusVOList);
     }

    //获取所有关注了当前用户的基本信息---谁关注了它
    @GetMapping("/getfocused")
    public CommonReturnType getFocused(@RequestParam("id")Integer id) throws BusinessException {
         //根据当前用户的信息，找出所有关注了当前用户的用户的基本信息
        List<FocusModel> focusModels = focusService.getFocusedUsers(id);
        //model --- view
        List<FocusVO> focusVOList =convertFromFocusModels(focusModels);
        return CommonReturnType.create(focusVOList);
    }

    /*
     建立关注关系
     如果之前存在关注的关系，现在就是互关（关注着与被关注着交换查找，那只需修改那行数据）
     如果不存在交换查询的结果则插入数据 save
     */
    @PostMapping("/add")
    public CommonReturnType addFocus(@RequestParam("nowId")Integer nowId,@RequestParam("userId")Integer userId) throws BusinessException {
        //调用service
        //封装数据
        FocusModel focusModel = new FocusModel();
        focusModel.setNowid(nowId);
        UserModel userModel = new UserModel();
        userModel.setId(userId);
        focusModel.setUserModel(userModel);
        //调用service
        focusService.addFocus(focusModel);

        return CommonReturnType.create(null);

    }
    //取消关注
    @GetMapping("/dele")
    public CommonReturnType delete(@RequestParam("id")Integer id){
        focusService.relieveFocus(id);
        return  CommonReturnType.create(null);
    }

    /**
     * 领域模型的转换
     * @param focusModels
     * @return
     * @throws BusinessException
     */
    private List<FocusVO> convertFromFocusModels(List<FocusModel> focusModels) throws BusinessException {
        List<FocusVO> focusVOList = new ArrayList<>();
        for(FocusModel model :focusModels){
            if(model==null||model.getUserModel()==null){
                throw new BusinessException(EmBusinessError.USER_NOT_EXIST.setErrorMsg("该关注关系的用户信息查找失败"));
            }else {
                UserVO userVO = new UserVO();
                FocusVO focusVO = new FocusVO();

                BeanUtils.copyProperties(model.getUserModel(),userVO);
                BeanUtils.copyProperties(model,focusVO);
                focusVO.setUserVO(userVO);
                focusVOList.add(focusVO);
            }
        }
        return focusVOList;
    }
}
