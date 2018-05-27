package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;

import java.util.List;

/**
 * @author: zhongtianemail@gmail.com
 * @create 2017-11-29 下午3:55
 * @Description:
 **/
public interface ICategoryService {
    ServerResponse addCategory(String categoryName, Integer parentId);
    ServerResponse updateCategoryName(Integer categoryId,String categoryName);
    ServerResponse<List<Category>> getChildParallelCategory(Integer categoryId);
    ServerResponse <List<Integer>> getCategoryIdRecurse(Integer categoryId);
}
