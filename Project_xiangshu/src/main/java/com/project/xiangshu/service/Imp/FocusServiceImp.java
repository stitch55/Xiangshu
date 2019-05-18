package com.project.xiangshu.service.Imp;

import com.project.xiangshu.entities.Focus;
import com.project.xiangshu.entities.User;
import com.project.xiangshu.error.BusinessException;
import com.project.xiangshu.error.EmBusinessError;
import com.project.xiangshu.model.servicemodel.FocusModel;
import com.project.xiangshu.model.servicemodel.userbasic.UserModel;
import com.project.xiangshu.repository.FocusRepository;
import com.project.xiangshu.repository.UserRepository;
import com.project.xiangshu.service.FocusService;
import com.project.xiangshu.validator.ValidationResult;
import com.project.xiangshu.validator.ValidatorImp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FocusServiceImp implements FocusService {
    @Autowired
    private FocusRepository focusRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ValidatorImp validatorImp;
    //添加关注关系
    @Override
    public void addFocus(FocusModel focusModel) throws BusinessException {
        //数据校验
        ValidationResult result = new ValidationResult();
        result= validatorImp.validator(focusModel);
        if(result.isHasError()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrorMsg());
        }
        //model---dataObject
        Focus focus = convertFromFocusModel(focusModel);
        //查询入库
//        //关注id 和被关注id 交换查询
//        Focus  Exfocus = focusRepository.getRelationship(focus.getFocusing_id(), focus.getFocused_id());
//        if(Exfocus!=null){
//            //存在交换查询的关系，就修改当前的关系
//            Exfocus.setRelationship(2);
//            //入库
//            focusRepository.save(focus);
//        }else {
//            //不存在交换查询的关系，就插入当前的关系
//            focus.setRelationship(1);
//            //model ---dataObject
//            //入库
            focusRepository.save(focus);
//        }
    }

    private Focus convertFromFocusModel(FocusModel focusModel) {
        Focus focus = new Focus();
        focus.setFocusing_id(focusModel.getNowid());
        focus.setFocused_id(focusModel.getUserModel().getId());
        return focus;
    }

    /**
     *
     * @param focusList
     * @param flag
     * @return
     * @throws BusinessException
     * 通过flag 判断是取所有当前用户关注的用户还是关注当前用户的用户
     */
    //获取关注关系的用户信息
    public List<FocusModel> getRelationship(List<Focus> focusList,boolean flag) throws BusinessException {
        if(focusList==null){
            throw  new BusinessException(EmBusinessError.NO_FOCUSRELAYIONSHIP);
        }else {
            //根据获取的该用户关注的用户的id 查询用户的基本信息
            Integer id ;
            List<User> userList = new ArrayList<>();
            for(Focus f:focusList){
                if(flag){
                    id = f.getFocusing_id();
                }else {
                    id=f.getFocused_id();
                }
                User user = getUserData(id);
                userList.add(user);
            }

            //定义fuocus 和user 的组装规则
            List<FocusModel> focusModels = new ArrayList<>();
            for(Focus f:focusList){
                for(User user:userList) {
                    if (flag) {
                        id = f.getFocusing_id();
                    } else {
                        id = f.getFocused_id();
                    }

                    if (id == user.getId()) {
                        FocusModel focusModel = convertFromFocus(f);
                        UserModel userModel = convertFromUser(user);
                        focusModel.setUserModel(userModel);
                        focusModels.add(focusModel);
                    }
                }
            }
            return focusModels;
        }
    }

    private FocusModel convertFromFocus(Focus f) {
        FocusModel focusModel = new FocusModel();
       BeanUtils.copyProperties(f,focusModel);
       return focusModel;
    }

    /**
     * 获取关注当前用户关所有用户的基本信息;
     * @param id
     * @return
     * @throws BusinessException
     */
    @Override
    public List<FocusModel> getFocusedUsers(Integer id) throws BusinessException {
        List<Focus> focusList = focusRepository.getFocusedUserById(id);
        //返回
        return getRelationship(focusList,true);
    }

    private UserModel convertFromUser(User user) throws BusinessException {
        if(user==null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST.setErrorMsg("关注用户信息查找失败"));
        }else {
            UserModel model = new UserModel();
            BeanUtils.copyProperties(user,model);
            return model;
        }
    }
    /**
     * 获取与当前用户有关系的用户基本信息
     * @param id
     * @return
     */
    private User getUserData(Integer id) {
        User user;
        Optional<User> Puser = userRepository.findById(id);
        try{
            user = Puser.get();
        }catch (Exception ex){
            user = null;
        }
        return user;
    }

    /**
     * 获取当前用户关注的所有用户大的基本信息
     * @param id
     * @return
     * @throws BusinessException
     */
    @Override
    public List<FocusModel> getFocusUsers(Integer id) throws BusinessException {
        System.out.println("id"+id);
        List<Focus> focusList = focusRepository.getFocusUserById(id);
        return getRelationship(focusList,false);
    }
    //解除关注关系
    @Override
    public void relieveFocus(Integer id) {
         focusRepository.deleteById(id);
    }
}
