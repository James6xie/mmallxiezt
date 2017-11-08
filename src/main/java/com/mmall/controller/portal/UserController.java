package com.mmall.controller.portal;

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

    /**
     * @author:zhongtianemail@gmail.com.cn
     * @Description:
     * @Param:
     * @Return: 
     * @Date:   17-11-8 下午2:49
     */
    @RequestMapping(value = "login.do",method = RequestMethod.POST)//limit request as POST
    @ResponseBody //format ret val as json
    public Object login(String username, String password, HttpSession session){

        
        return null;
    }

}
