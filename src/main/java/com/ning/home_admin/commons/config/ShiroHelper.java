package com.ning.home_admin.commons.config;

import com.ning.home_admin.commons.annotation.Helper;
import org.apache.shiro.authz.AuthorizationInfo;

/**
 * @author NKW
 */
@Helper
public class ShiroHelper extends ShiroRealm {

    /**
     * 获取当前用户的角色和权限集合
     *
     * @return AuthorizationInfo
     */
    public AuthorizationInfo getCurrentuserAuthorizationInfo() {
        return super.doGetAuthorizationInfo(null);
    }
}
