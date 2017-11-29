package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.pojo.Category;
import com.mmall.service.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

/**
 * @author: zhongtianemail@gmail.com
 * @create 2017-11-29 下午3:55
 * @Description:
 **/
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService{

    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ServerResponse addCategory(String categoryName,Integer parentId){
        if (parentId == null && StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("添加品类参数错误");
        }

        Category category = new Category();

        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);

        int rowCount = categoryMapper.insert(category);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("品类添加成功");
        }
        return ServerResponse.createByErrorMessage("品类添加失败");
    }

    @Override
    public ServerResponse updateCategoryName(Integer categoryId,String categoryName){
        if(categoryId == null && StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("更新品类参数失败");
        }

        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);

        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("更新品类名称成功");
        }
        return ServerResponse.createByErrorMessage("更新品类名称失败");
    }

    @Override
    public ServerResponse<List<Category>> getChildParallelCategory(Integer categoryId){
        List<Category> categoryList = categoryMapper.selectChildParallelCategoryByPid(categoryId);
        if(categoryList.isEmpty()){
            logger.info("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(categoryList);
    }

    /**
     * @author:zhongtianemail@gmail.com.cn
     * @Description:递归查询本节点的id以及child节点的id
     * @Param:
     * @Return: 
     * @Date:   17-11-29 下午6:27
     */
    @Override
    public ServerResponse  getCategoryIdRecurse(Integer categoryId){
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet,categoryId);

        List<Integer> categoryIdList = Lists.newArrayList();
        if(categoryId != null){
            for (Category categoryItem:
                 categorySet) {
                categoryIdList.add(categoryItem.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryIdList);
    }

    /**
     * @author:zhongtianemail@gmail.com.cn
     * @Description:递归查找的实现方法，递归查出子节点；
     * @Param:
     * @Return: 
     * @Date:   17-11-29 下午6:30
     */
    private Set<Category> findChildCategory(Set<Category> categorySet,Integer categoryId){
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if(category != null){
            categorySet.add(category);
        }
        //查找子节点，如果子节点为空（递归的退出条件）则退出
        List<Category> categoryList = categoryMapper.selectChildParallelCategoryByPid(categoryId);
        for (Category categoryItem :
             categoryList) {
            findChildCategory(categorySet,categoryItem.getId());
        }
        return categorySet;
    }
}
