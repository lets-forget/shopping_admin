package com.ning.home_admin.commons.runner;


import com.ning.home_admin.commons.properties.ShoppingProperties;
import com.ning.home_admin.monitor.service.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * @author NKW
 */
@Slf4j
@Component
public class FebsStartedUpRunner implements ApplicationRunner {

    @Autowired
    private ConfigurableApplicationContext context;
    @Autowired
    private ShoppingProperties febsProperties;
    @Autowired
    private IRedisService redisService;

    @Value("${server.port:8080}")
    private String port;
    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            // 测试 Redis连接是否正常
            redisService.exists("febs_test");
        } catch (Exception e) {
            log.error("启动失败，{}", e.getMessage());
            log.error("Redis连接异常，请检查Redis连接配置并确保Redis服务已启动");
            // 关闭 FEBS
            context.close();
        }
        if (context.isActive()) {
            InetAddress address = InetAddress.getLocalHost();
            String url = String.format("http://%s:%s", address.getHostAddress(), port);
            String loginUrl = febsProperties.getShiro().getLoginUrl();
            if (StringUtils.isNotBlank(contextPath))
                url += contextPath;
            if (StringUtils.isNotBlank(loginUrl))
                url += loginUrl;
            log.info("权限系统启动完毕，地址：{}", url);
/*            String os = System.getProperty("os.name");
            // 默认为 windows时才自动打开页面
            if (StringUtils.containsIgnoreCase(os, "windows")) {
                //使用默认浏览器打开系统登录页
                Runtime.getRuntime().exec("cmd  /c  start " + url);
            }*/
        }
    }
}
