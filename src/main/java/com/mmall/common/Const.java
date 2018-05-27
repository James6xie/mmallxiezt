package com.mmall.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author: zhongtianemail@gmail.com
 * @create 2017-11-13 下午6:08
 * @Description:
 **/
public class Const {
    public static final String CURRENT_USER = "currentUser";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc","price_asc");
    }

    public interface Role{
        int ROLE_CUSTOM = 0;
        int ROLE_ADMIN = 1;
    }

    public enum ProductStatusEnum{
        ON_SALE(1,"在线");
        private String value;
        private int code;

        ProductStatusEnum(int code, String value) {
            this.value = value;
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

    }
}
