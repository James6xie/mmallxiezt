package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author: zhongtianemail@gmail.com
 * @create 2017-11-29 下午3:34
 * @Description:
 **/
@Controller
@RequestMapping(value = "/manage/category")
public class CategoryManagerController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping("add_category.do")
    @ResponseBody
    public ServerResponse addCategory(HttpSession session, String categoryName, @RequestParam(value = "parentId",defaultValue="0") int parentId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        //校验是否为管理员
        if (iUserService.checkAminRole(user).isSuccess()){
            //是管理员，增加处理分类的逻辑
            return iCategoryService.addCategory(categoryName,parentId);
        }else {
            return ServerResponse.createByErrorMessage("没有权限操作，需要管理员权限");
        }
    }

    /**
     * @author:zhongtianemail@gmail.com.cn
     * @Description:更新category名称
     * @Param:
     * @Return: 
     * @Date:   17-11-29 下午4:37
     */
    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session,Integer categortId,String categoryName){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        if(iUserService.checkAminRole(user).isSuccess()){
            //更新categoryname
            return iCategoryService.updateCategoryName(categortId,categoryName);
        }else {
            return ServerResponse.createByErrorMessage("没有权限操作，不是管理员用户");
        }
    }

    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildParallelCategory(HttpSession session,@RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        if(iUserService.checkAminRole(user).isSuccess()){
            //查询子节点的category信息，只查平级（不递归查询）
            return iCategoryService.getChildParallelCategory(categoryId);
        }else {
            return ServerResponse.createByErrorMessage("没有权限，不是管理员用户");
        }
    }

    @RequestMapping("get_category_recurse.do")
    @ResponseBody
    public ServerResponse getCategoryIdRecurse(HttpSession session,@RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        if(iUserService.checkAminRole(user).isSuccess()){
            //查询当前节点Id并递归查询子节点的Id 0 --->10001-->10001001
            return iCategoryService.getCategoryIdRecurse(categoryId);
        }else {
            return ServerResponse.createByErrorMessage("没有权限，需要管理员权限");
        }
    }
}
