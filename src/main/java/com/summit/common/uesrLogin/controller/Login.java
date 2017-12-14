package com.summit.common.uesrLogin.controller;

import com.summit.common.uesrLogin.service.UserManagerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("userlogin")
public class Login {
    @Autowired
    UserManagerService userManagerService;

    /**
     * 用于处理全局异常
     *
     * @return
     */
    @ExceptionHandler({Exception.class})
    public String exception(Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
        return e.getMessage();
    }

    @RequestMapping("login")
    public String login(HttpServletRequest request) {
        // List<User> userList = userManagerService.getUserList();

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        password = "1f82c942befda29b6ed487a51da199f78fce7f05";
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        token.setRememberMe(true);

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException e1) {
            e1.printStackTrace();
//            return "redirect:/pages/common/error.jsp";//重定向到错误页
//            return "/common/wsdemo";
            return "/common/sockjsdemo";
        } catch (AuthenticationException e2) {
            e2.printStackTrace();
            return "common/error";
        } catch (Exception e3) {
            e3.printStackTrace();
            return "common/error";
        }
        return "common/success";
    }

    @RequestMapping("demo")
    public String abcd(HttpServletRequest request) {
        return "redirect:/html/index.html";
    }
}