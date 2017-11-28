package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author: zhongtianemail@gmail.com
 * @create 2017-11-08 下午2:44
 * @Description:
 **/
@Controller
@RequestMapping(value = "/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;
    /**
     * @author:zhongtianemail@gmail.com.cn
     * @Description:
     * @Param:
     * @Return: 
     * @Date:   17-11-8 下午2:49
     */
    @RequestMapping(value = "login.do",method = RequestMethod.POST)//limit request as POST
    @ResponseBody //format ret val as json
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response = iUserService.login(username,password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    @RequestMapping(value = "logout.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "register.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user){
        return iUserService.register(user);
    }
    
    /**
     * @author:zhongtianemail@gmail.com.cn
     * @Description:验证用户信息，根据parm2判断字符串parm1所代表内容是否存在
     * @Param1:str
     * @Param2:type
     * @Return:
     * @Date:   17-11-24 下午4:30
     */
    @RequestMapping(value = "check_valid.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String str, String type){
        return iUserService.checkValid(str,type);
    }

    /**
     * @author:zhongtianemail@gmail.com.cn
     * @Description:获取用户信息
     * @Param:
     * @Return: 
     * @Date:   17-11-24 下午4:56
     */
    @RequestMapping(value = "get_user_info.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user != null){
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
    }

    //忘记密码
    @RequestMapping(value = "forget_get_question.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username){
        return iUserService.selectQuestion(username);
    }

    //校验问题答案
    @RequestMapping(value = "forget_check_answer.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String>forgetCheckAnswer(String username, String question, String answer){
        return iUserService.checkAnswer(username, question, answer);
    }

    //忘记密码重置密码
    @RequestMapping(value = "forget_reset_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken){
        return iUserService.forgetResetPassword(username, passwordNew, forgetToken);
    }

    @RequestMapping(value = "reset_password.do",method = RequestMethod.POST)
    @ResponseBody
    //重置密码
    public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iUserService.resetPassword(passwordOld,passwordNew,user);
    }

    @RequestMapping(value = "update_information.do",method = RequestMethod.POST)
    @ResponseBody
    //更新用户个人信息
    public ServerResponse<User> updateInformation(HttpSession session, User user){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        //赋值为当前登录的userId和username
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        ServerResponse<User> response = iUserService.updateInformation(user);
        if(response.isSuccess()){
            //response.getData().setUsername(currentUser.getUsername());
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }
    @RequestMapping(value = "get_information.do",method = RequestMethod.POST)
    @ResponseBody
    //获取用户个人信息
    public ServerResponse<User> getInformation(HttpSession session){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，需要强制登录status=10");
        }
        return iUserService.getInformation(currentUser.getId());
    }
}
