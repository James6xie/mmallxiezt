package com.mmall.service.impl;

import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.dao.ProductMapper;
import com.mmall.pojo.Product;
import com.mmall.service.IProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zhongtianemail@gmail.com
 * @create 2017-12-01 下午3:19
 * @Description:
 **/
@Service("iProductService")
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ServerResponse saveOrUpdateProduct(Product product){

        if(product != null){
            if(StringUtils.isNotBlank(product.getSubImages())){
                String[] subImageArray = product.getSubImages().split(",");
                if(subImageArray.length > 0){
                    product.setMainImage(subImageArray[0]);
                }
            }
            if(product.getId() != null){
                int rowCount = productMapper.updateByPrimaryKey(product);
                if(rowCount > 0){
                    return ServerResponse.createBySuccess("产品更新传成功");
                }
                return ServerResponse.createByErrorMessage("产品更新失败");
            }else {
                int rowCount = productMapper.insert(product);
                if(rowCount > 0){
                    return ServerResponse.createBySuccess("添加产品成功");
                }
                return ServerResponse.createByErrorMessage("添加产品失败");
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新产品失败");
    }

    @Override
    public ServerResponse setSaleStatus(Integer productId, Integer status){
        if(productId == null && status ==null ){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLLEGAL_ARGUMENT.getCode(),ResponseCode.ILLLEGAL_ARGUMENT.getDesc());
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("产品销售状态设置成功");
        }
        return ServerResponse.createByErrorMessage("产品销售状态设置失败");
    }
}
