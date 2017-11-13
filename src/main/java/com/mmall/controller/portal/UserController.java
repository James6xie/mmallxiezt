package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
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

}
