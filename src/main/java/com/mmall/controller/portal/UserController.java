package com.mmall.controller.portal;

import com.mmall.common.Const;
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

    @RequestMapping(value = "logout.do",method = RequestMethod.GET)//limit request as GET
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "register.do",method = RequestMethod.GET)//limit request as GET
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
    @RequestMapping(value = "check_valid.do",method = RequestMethod.GET)//limit request as GET
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
    @RequestMapping(value = "get_user_info.do",method = RequestMethod.GET)//limit request as GET
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user != null){
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
    }

    //忘记密码
    @RequestMapping(value = "forget_get_question.do",method = RequestMethod.GET)//limit request as GET
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username){
        return iUserService.selectQuestion(username);
    }

    //校验问题答案
    public ServerResponse<String>forgetCheckAnswer(String username, String question, String answer){
        int resultCount = UserMapper.
    }
}
