package com.ning.home_admin.monitor.mapper;

import com.ning.home_admin.commons.entity.Page;
import com.ning.home_admin.monitor.entity.LoginLog;
import com.ning.home_admin.sytem.pojo.User;

import java.util.List;
import java.util.Map;

public interface LoginLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoginLog record);

    LoginLog selectByPrimaryKey(Integer id);

    List<LoginLog> selectAll();

    int updateByPrimaryKey(LoginLog record);

    List<LoginLog> selectAllByUsername(Page page);

    void deleteAllByIds(String[] loginLogIds);

    Long findTotalVisitCount();

    Long findTodayVisitCount();

    Long findTodayIp();

    List<Map<String, Object>> findLastSevenDaysVisitCount(User user);
}