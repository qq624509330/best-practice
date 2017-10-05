package io.spldeolin.bestpractice.shiro.component;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import java.util.HashSet;
import java.util.Set;

public class Realm extends AuthorizingRealm {

    private final String USERNAME = "deolin";

    private final String PASSWORD = "1";

    /** 授权 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Set<String> roleNames = new HashSet<>();
        roleNames.add("admin");
        Set<String> permissionNames = new HashSet<>();
        roleNames.add("view");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roleNames);
        authorizationInfo.setStringPermissions(permissionNames);
        return authorizationInfo;
    }

    /** 授权 */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        if (StringUtils.equals(USERNAME, username) && StringUtils.equals(PASSWORD, password)) {
            return new SimpleAuthenticationInfo(USERNAME, DigestUtils.md5(PASSWORD), getName());
        } else {
            throw new AuthenticationException("登录失败");
        }
    }

}
