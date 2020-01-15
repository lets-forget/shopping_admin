package com.ning.home_admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
@MapperScan("com.ning.home_admin.*.mapper")
public class HomeAdminApplication  {

    public static void main(String[] args) {
        SpringApplication.run(HomeAdminApplication.class, args);
    }

}
