package com.summit.framework.shiro;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (request.getAttribute(getFailureKeyAttribute()) != null) {
            return true;
        }
        return super.onAccessDenied(request, response);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
//        WebUtils.issueRedirect(request, response, getSuccessUrl());
//        return false;
        return super.onLoginSuccess(token, subject, request, response);
    }
}
