package com.summit.common.uesrLogin.controller;

import com.summit.base.CommonResult;
import com.summit.base.Constants;
import com.summit.base.exception.CustomException;
import com.summit.common.uesrLogin.service.UserManagerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("userlogin")
public class Login {
    @Autowired
    UserManagerService userManagerService;

    /**
     * 用于处理异常
     *
     * @return
     * @ExceptionHandler 的方式只能处理当前类的异常，处理全局异常可以用@ControllerAdvice
     */
//    @ExceptionHandler({Exception.class})
//    @ResponseBody
//    public CommonResult exception(Exception e) {
//        CommonResult result = new CommonResult();
//        System.out.println(e.getMessage());
//        e.printStackTrace();
//        result.setSuccess(false);
//        result.setErrorText(e.getMessage());
//        return result;
//    }
    @RequestMapping("login")
    public String login(HttpServletRequest request) throws Exception {

        //如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        //根据shiro返回的异常类路径判断，抛出指定异常信息
        if (exceptionClassName != null) {
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                //最终会抛给异常处理器
                throw new CustomException("账号不存在", Constants.ERROR_CODE_ACCOUNT_NOT_EXIST);
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                throw new CustomException("用户名/密码错误", Constants.ERROR_CODE_ACCOUNT_OR_PASSWORD_ERROR);
            } else if (Constants.FAILURE_VALUE_ATTRIBUTE.equals(exceptionClassName)) {
                throw new CustomException("验证码错误", Constants.ERROR_CODE_VALIDATECODE_ERROR);
            } else {
                throw new CustomException("未知错误", Constants.ERROR_CODE_UNKNOWN);//最终在异常处理器生成未知错误
            }
        }

        // List<User> userList = userManagerService.getUserList();

        String name = request.getParameter("username");
        String password = request.getParameter("password");
        password = "1f82c942befda29b6ed487a51da199f78fce7f05";
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        token.setRememberMe(true);

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (Exception e) {
            if (e instanceof UnknownAccountException) {
                throw new CustomException("账号不存在", Constants.ERROR_CODE_ACCOUNT_NOT_EXIST);
//            return "redirect:/pages/common/error.jsp";//重定向到错误页
//            return "/common/wsdemo";
//            return "/common/sockjsdemo";
            } else if (e instanceof AuthenticationException) {
                throw new CustomException("密码错误", Constants.ERROR_CODE_ACCOUNT_NOT_EXIST);
            } else {
                throw e;
            }
        }
        return "redirect:/pages/common/error.jsp";
    }

    @RequestMapping("checkValidateCode")
    @ResponseBody
    public CommonResult checkValidateCode(HttpServletRequest request) throws Exception {
        CommonResult commonResult = new CommonResult();
        //如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        //根据shiro返回的异常类路径判断，抛出指定异常信息
        if (exceptionClassName != null) {
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                //最终会抛给异常处理器
                throw new CustomException("账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                throw new CustomException("用户名/密码错误");
            } else if (Constants.FAILURE_VALUE_ATTRIBUTE.equals(exceptionClassName)) {
                throw new CustomException("验证码错误 ");
            } else {
                throw new CustomException("未知错误");//最终在异常处理器生成未知错误
            }
        }
        return commonResult;
    }

    @RequestMapping("demo")
    public String abcd(HttpServletRequest request) {
        return "redirect:/html/index.html";
    }
}