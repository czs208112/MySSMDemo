package com.summit.base;

public class Constants {

    /**
     * 生成后存储在session中的验证码字段名
     */
    public static final String VALIDATECODE_SEEESION_ATTRIBUTE = "sessionStoredValidateCode";
    /**
     * 验证码认证失败后存储到的属性对应的value值
     */
    public static final String FAILURE_VALUE_ATTRIBUTE = "validateCodeError";

    /********************自定义错误代码开始****************/
    public static final long ERROR_CODE_UNKNOWN = 9999; //未知错误
    public static final long ERROR_CODE_VALIDATECODE_ERROR = 1000; //验证码错误
    public static final long ERROR_CODE_ACCOUNT_NOT_EXIST = 1010; //账号不存在
    public static final long ERROR_CODE_ACCOUNT_OR_PASSWORD_ERROR = 1011; //账号或者密码错误

    /********************自定义错误代码开始****************/

}
