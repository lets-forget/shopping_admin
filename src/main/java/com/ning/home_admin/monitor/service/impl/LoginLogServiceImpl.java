package com.ning.home_admin.monitor.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ning.home_admin.commons.entity.Page;
import com.ning.home_admin.commons.utils.AddressUtil;
import com.ning.home_admin.commons.utils.HttpContextUtil;
import com.ning.home_admin.commons.utils.IPUtil;
import com.ning.home_admin.commons.utils.PageUtil;
import com.ning.home_admin.monitor.entity.LoginLog;
import com.ning.home_admin.monitor.mapper.LoginLogMapper;
import com.ning.home_admin.monitor.service.ILoginLogService;
import com.ning.home_admin.sytem.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author NKW
 */
@Service("loginLogService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LoginLogServiceImpl implements ILoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public void saveLoginLog(LoginLog loginLog) {
        loginLog.setLoginTime(new Date());
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtil.getIpAddr(request);
        loginLog.setIp(ip);
        loginLog.setLocation(AddressUtil.getCityInfo(ip));
        loginLogMapper.insert(loginLog);
    }

    @Override
    public PageUtil findLoginLogs(Page page) {
        PageUtil pageUtil=new PageUtil();
        if (page.getPage() == null||page.getPage()<=0)page.setPage(1);
        if (page.getLimit() == null||page.getLimit()<=0)page.setLimit(10);
        PageHelper.startPage(page.getPage(),page.getLimit());
        List<LoginLog> loginLogs = loginLogMapper.selectAllByUsername(page);
        PageInfo<LoginLog> pageInfo=new PageInfo<>(loginLogs);

        pageUtil.setData(pageInfo.getList());
        pageUtil.setCount(pageInfo.getTotal());

        return pageUtil;
    }

    @Override
    public void deleteById(Integer id) {
        loginLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteAllByIds(String[] loginLogIds) {
        loginLogMapper.deleteAllByIds(loginLogIds);
    }

    @Override
    public Long findTotalVisitCount() {
        return loginLogMapper.findTotalVisitCount();
    }

    @Override
    public Long findTodayVisitCount() {
        return loginLogMapper.findTodayVisitCount();
    }

    @Override
    public Long findTodayIp() {
        return loginLogMapper.findTodayIp();
    }

    @Override
    public List<Map<String, Object>> findLastSevenDaysVisitCount(User user) {
        return loginLogMapper.findLastSevenDaysVisitCount(user);
    }
}
