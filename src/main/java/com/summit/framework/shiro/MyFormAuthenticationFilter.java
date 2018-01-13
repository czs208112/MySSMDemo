package com.summit.framework.shiro;

import com.summit.base.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * 回调方法返回布尔类型，通常表示流程是否继续往下走。
 * true: 流程继续往下走,本项目中会走到Controller， false：流程不往下走，本项目中不走Controller了
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(MyFormAuthenticationFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //是否有其它拦截器写入的错误信息,如本项目中验证码拦截器写入的验证码错误信息
        if (request.getAttribute(getFailureKeyAttribute()) != null) {
            return true;
        }

        //是否登陆请求，即请求的url是否为xml中配置的loginUrl
        if (this.isLoginRequest(request, response)) {

            //是否post请求
            if (this.isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }

                //请求为post登陆请求，执行登陆验证操作
                return this.executeLogin(request, response);
            } else {

                //请求为get登陆请求（通常为重定向的目标地址），
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }

                //重定向到请求登陆页面(页面地址由常量读取，xml配置的loginUrl在本项目中只是登陆请求的地址,当然你也可以配置为登陆页面地址，但会面临其它的问题要解决)
                WebUtils.issueRedirect(request, response, Constants.LOGIN_PAGE_URL);
                return false;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the Authentication url [" + this.getLoginUrl() + "]");
            }
            this.saveRequestAndRedirectToLogin(request, response);
            return false;
        }

//        return super.onAccessDenied(request, response);
    }

    /**
     * executeLogin执行成功后回调该方法
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {

//        WebUtils.issueRedirect(request, response, getSuccessUrl());//登陆成功后直接跳转到配置的successUrl
//        return false;

//        return super.onLoginSuccess(token, subject, request, response);//登陆成功后走默认流程

        //处理session,实现一个用户同一时刻只能在一个地方登录
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();//获取当前已登录的用户session列表
        for (Session session : sessions) {
            //清除该用户以前登录时保存的session
            String userName = request.getParameter(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
            HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
            String sessionId = httpServletRequest.getSession().getId();
            if (userName.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY))) && !sessionId.equals(session.getId())) {
                sessionManager.getSessionDAO().delete(session);
            }
        }

        return true; //所有登陆成功后的转向交给Controller来解决,本来shiro是可以跳转的，但是由于登陆采用ajax请求，无法直接跳转页面。
    }

    /**
     * executeLogin执行失败后回调该方法
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        return super.onLoginFailure(token, e, request, response);
    }
}
