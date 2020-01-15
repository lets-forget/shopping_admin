package com.ning.home_admin.monitor.service;

import com.ning.home_admin.commons.entity.Page;
import com.ning.home_admin.commons.utils.PageUtil;
import com.ning.home_admin.monitor.entity.LoginLog;
import com.ning.home_admin.sytem.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @author NKW
 */
public interface ILoginLogService  {

    /**
     * 保存登录日志
     *
     * @param loginLog 登录日志
     */
    void saveLoginLog(LoginLog loginLog);


    /**
     * 获取登录日志分页信息
     *
     * @return PageUtil
     */
    PageUtil findLoginLogs(Page page);

    /**
     * 删除一条数据
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 删除多个id
     * @param loginLogIds
     */
    void deleteAllByIds(String[] loginLogIds);

    /**
     * 总访问
     * @return Long
     */
    Long findTotalVisitCount();

    /**
     * 查询今日访问的次数
     * @return Long
     */
    Long findTodayVisitCount();

    /**
     * 获取系统今日访问 IP数
     *
     * @return Long
     */
    Long findTodayIp();

    /**
     * 获取近期系统访问记录
     * @param user
     * @return
     */
    List<Map<String, Object>> findLastSevenDaysVisitCount(User user);
}
