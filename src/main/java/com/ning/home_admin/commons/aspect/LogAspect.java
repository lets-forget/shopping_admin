package com.ning.home_admin.commons.aspect;


import com.ning.home_admin.commons.properties.ShoppingProperties;
import com.ning.home_admin.commons.utils.HttpContextUtil;
import com.ning.home_admin.commons.utils.IPUtil;
import com.ning.home_admin.monitor.entity.Log;
import com.ning.home_admin.monitor.service.ILogService;
import com.ning.home_admin.sytem.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * AOP 记录用户操作日志
 *
 * @author NKW
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private ShoppingProperties febsProperties;

    @Autowired
    private ILogService logService;

    @Pointcut("@annotation(com.ning.home_admin.commons.annotation.Log)")
    public void pointcut() {
        // do nothing
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result;
        long beginTime = System.currentTimeMillis();
        // 执行方法
        result = point.proceed();
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        // 设置 IP地址
        String ip = IPUtil.getIpAddr(request);
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        if (febsProperties.isOpenAopLog()) {
            // 保存日志
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            Log log = new Log();
            if (user != null)
                log.setUsername(user.getUsername());
            log.setIp(ip);
            log.setTime(time);
            logService.saveLog(point, log);
        }
        return result;
    }
}
