package com.mmall.service;

import com.mmall.common.ServerResponse;

/**
 * @author: zhongtianemail@gmail.com
 * @create 2017-11-29 下午3:55
 * @Description:
 **/
public interface ICategoryService {
    ServerResponse addCategory(String categoryName, Integer parentId);
    ServerResponse updateCategoryName(Integer categoryId,String categoryName);
}
