package com.ning.home_admin.sytem.controller;

import com.ning.home_admin.commons.annotation.Limit;
import com.ning.home_admin.commons.controller.BaseController;
import com.ning.home_admin.commons.entity.FebsResponse;
import com.ning.home_admin.commons.exception.FebsException;
import com.ning.home_admin.commons.utils.CaptchaUtil;
import com.ning.home_admin.commons.utils.MD5Util;
import com.ning.home_admin.monitor.entity.LoginLog;
import com.ning.home_admin.monitor.service.ILoginLogService;
import com.ning.home_admin.sytem.pojo.User;
import com.ning.home_admin.sytem.service.IUserService;
import com.wf.captcha.base.Captcha;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController extends BaseController {

    @Autowired
    private ILoginLogService loginLogService;

    @Autowired
    private IUserService userService;

    @PostMapping("/admin/login")
    @Limit(key = "admin", period = 60, count = 10, name = "登录接口", prefix = "limit")
    public FebsResponse login(
             String username,
             String password,
             String verifyCode,
            boolean rememberMe, HttpServletRequest request) throws FebsException {
        if (!CaptchaUtil.verify(verifyCode, request)) {
            throw new FebsException("验证码错误！");
        }
        password = MD5Util.encrypt(username.toLowerCase(), password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        super.login(token);
        // 保存登录日志
        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(username);
        loginLog.setSystemBrowserInfo();
       this.loginLogService.saveLoginLog(loginLog);

        return new FebsResponse().success();
    }


    @GetMapping("/admin/index")
    public FebsResponse index(){
        User user = super.getCurrentUser();
        // 更新登录时间
        this.userService.updateLoginTime(user.getUsername());
        Map<String, Object> data = new HashMap<>();
        // 获取系统访问记录
        Long totalVisitCount = this.loginLogService.findTotalVisitCount();
        data.put("totalVisitCount", totalVisitCount);
        Long todayVisitCount = this.loginLogService.findTodayVisitCount();
        data.put("todayVisitCount", todayVisitCount);
        Long todayIp = this.loginLogService.findTodayIp();
        data.put("todayIp", todayIp);
        // 获取近期系统访问记录
        List<Map<String, Object>> lastSevenVisitCount = this.loginLogService.findLastSevenDaysVisitCount(null);
        data.put("lastSevenVisitCount", lastSevenVisitCount);
        List<Map<String, Object>> lastSevenUserVisitCount = this.loginLogService.findLastSevenDaysVisitCount(user);
        data.put("lastSevenUserVisitCount", lastSevenUserVisitCount);
        return new FebsResponse().success().data(data);
    }
    @GetMapping("/images/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.outPng(110, 34, 4, Captcha.TYPE_ONLY_NUMBER, request, response);
    }

}
