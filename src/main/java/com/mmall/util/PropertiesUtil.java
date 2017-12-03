package com.mmall.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author: zhongtianemail@gmail.com
 * @create 2017-11-28 上午11:06
 * @Description:
 **/
public class PropertiesUtil {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties props;

    //  静态代码块会在类加载的时候被执行有且只有一次；
    static {
        String fileName = "mmall.properties";
        props = new Properties();
        try {
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8"));
        }catch (IOException e){
            logger.error("配置文件读取异常", e);
        }
    }

    public static String getProperty(String key){
        String value = props.getProperty(key);
        if(StringUtils.isBlank(value)){
            return null;
        }
        return value.trim();
    }

    public static String getProperty(String key,String defaultKey){
        String value = props.getProperty(key);
        if (StringUtils.isBlank(value)){
            return defaultKey;
        }
        return value.trim();
    }
}
