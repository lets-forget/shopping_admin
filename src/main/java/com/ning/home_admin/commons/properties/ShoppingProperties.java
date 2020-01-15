package com.ning.home_admin.commons.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:shrio.properties"})
@ConfigurationProperties(prefix = "febs")
public class ShoppingProperties {

    ShiroProperties shiro=new ShiroProperties();
    boolean openAopLog;
}
