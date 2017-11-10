package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;

/**
 * @author: zhongtianemail@gmail.com
 * @create 2017-11-08 下午2:56
 * @Description:
 **/
public interface IUserService {

    ServerResponse<User> login(String username, String password);
}
