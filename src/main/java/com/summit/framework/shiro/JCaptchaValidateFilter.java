package com.summit.framework.shiro;

import com.alibaba.druid.util.StringUtils;
import com.summit.base.Constants;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JCaptchaValidateFilter extends AccessControlFilter {

    /**
     * 是否开启验证码验证   默认true
     */
    private boolean jcaptchaEbabled = true;

    /**
     * 前台提交的验证码参数名
     */
    private String jcaptchaParam = "validateCode";

    /**
     * 验证失败后存储到的属性名
     */
    private String failureKeyAttribute = "shiroLoginFailure";

    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        //1、设置验证码是否开启属性，页面可以根据该属性来决定是否显示验证码
        request.setAttribute("jcaptchaEbabled", jcaptchaEbabled);

        HttpServletRequest httpRequest = WebUtils.toHttp(request);

        //2、判断验证码是否禁用 或不是表单提交（允许访问）
        if (jcaptchaEbabled == false || !"post".equalsIgnoreCase(httpRequest.getMethod())) {
            return true;
        }

        //表单提交，校验验证码的正确性
        String storedCode = getSubject(request, response).getSession().getAttribute(Constants.VALIDATECODE_SEEESION_ATTRIBUTE).toString();
        String currentCode = httpRequest.getParameter(jcaptchaParam);

        return StringUtils.equalsIgnoreCase(storedCode, currentCode);
    }

    protected boolean onAccessDenied(ServletRequest request, ServletResponse servletResponse) throws Exception {
        //如果验证码失败了，存储失败 key 属性
        request.setAttribute(failureKeyAttribute, Constants.FAILURE_VALUE_ATTRIBUTE);
        return true;
    }

    public String getFailureKeyAttribute() {
        return failureKeyAttribute;
    }
    public void setFailureKeyAttribute(String failureKeyAttribute) {
        this.failureKeyAttribute = failureKeyAttribute;
    }
    public String getJcaptchaParam() {
        return jcaptchaParam;
    }
    public void setJcaptchaParam(String jcaptchaParam) {
        this.jcaptchaParam = jcaptchaParam;
    }
    public boolean isJcaptchaEbabled() {
        return jcaptchaEbabled;
    }
    public void setJcaptchaEbabled(boolean jcaptchaEbabled) {
        this.jcaptchaEbabled = jcaptchaEbabled;
    }


}