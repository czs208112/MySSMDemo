package com.summit.framework.shiro;

import com.summit.common.uesrLogin.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.summit.common.uesrLogin.dao.IUserManagerMapper;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    IUserManagerMapper userDao;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principal) {
//		String username = (String) principal.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        // Set<String> roles = userDao.findUserRoles(username);
        // authorizationInfo.setRoles(roles);
        //
        // Set<String> permissions = userDao.findUserPermissions(username);
        // authorizationInfo.setStringPermissions(permissions);

        return authorizationInfo;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        // UsernamePasswordToken login_token = (UsernamePasswordToken) token;
        //
        // //校验码判断逻辑
        // //取得用户输入的校验码
        // String userInputValidCode = login_token.getValidCode();
        //
        // //取得真实的正确校验码
        // String realRightValidCode = (String)
        // SecurityUtils.getSubject().getSession().getAttribute("rand");
        //
        // if (null == userInputValidCode ||
        // !userInputValidCode.equalsIgnoreCase(realRightValidCode)) {
        // throw new ValidCodeException("验证码输入不正确");
        // }

        // 以上校验码验证通过以后,查数据库
        // UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)
        // token;// 封装认证对象
        // User user =
        // userDao.findByUsername(usernamePasswordToken.getUsername());

        String username = (String) token.getPrincipal();
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new UnknownAccountException();// 没找到帐号
        }

        // if (Boolean.FALSE.equals(user.getEnable())) {
        // throw new LockedAccountException(); // 帐号锁定
        //
        // }

        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUserName(), // 用户名
//                user.getPassword(), // 密码
                token.getCredentials(), //为了认证通过，密码值取界面值，实际业务绝对不会这样
                getName() // realm name

        );

        return authenticationInfo;
    }

}
