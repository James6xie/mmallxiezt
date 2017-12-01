package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;

/**
 * @author: zhongtianemail@gmail.com
 * @create 2017-12-01 下午3:18
 * @Description:
 **/
public interface IProductService {
    ServerResponse saveOrUpdateProduct(Product product);
    ServerResponse setSaleStatus(Integer productId, Integer status);
}
