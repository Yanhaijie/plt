package com.supermoney.open.platform.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2018-01-10.
 */
@Component
@ConfigurationProperties(prefix = "conf")
@PropertySource("classpath:application.properties")
public class AppProperties {
    /**
     * 文件服务器地址
     */
    private  String fastdfsHost;

    public String getFastdfsHost() {
        return fastdfsHost;
    }

    public void setFastdfsHost(String fastdfsHost) {
        this.fastdfsHost = fastdfsHost;
    }
}
