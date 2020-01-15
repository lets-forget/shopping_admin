package com.ning.home_admin.monitor.service;


import com.ning.home_admin.monitor.entity.ActiveUser;

import java.util.List;

/**
 * @author NKW
 */
public interface ISessionService {

    /**
     * 获取在线用户列表
     *
     * @return List<ActiveUser>
     */
    List<ActiveUser> list(String username);

    /**
     * 踢出用户
     *
     * @param sessionId sessionId
     */
    void forceLogout(String sessionId);
}
