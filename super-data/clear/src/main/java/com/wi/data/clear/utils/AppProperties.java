package com.wi.data.clear.utils;

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
     * 源主机ip
     */
    private String sourceHost;
    /**
     * 源主机端口
     */
    private int sourcePort;

    /**
     * 目标主机ip
     */
    private String targetHost;
    /**
     * 目标主机端口
     */
    private int targetPort;

    /**
     * 源集群名称
     */
    private String sourceCluster;

    /**
     * 目标集群名称
     */
    private String targetCluster;

    public String getSourceHost() {
        return sourceHost;
    }

    public void setSourceHost(String sourceHost) {
        this.sourceHost = sourceHost;
    }

    public int getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(int sourcePort) {
        this.sourcePort = sourcePort;
    }

    public String getTargetHost() {
        return targetHost;
    }

    public void setTargetHost(String targetHost) {
        this.targetHost = targetHost;
    }

    public int getTargetPort() {
        return targetPort;
    }

    public void setTargetPort(int targetPort) {
        this.targetPort = targetPort;
    }

    public String getSourceCluster() {
        return sourceCluster;
    }

    public void setSourceCluster(String sourceCluster) {
        this.sourceCluster = sourceCluster;
    }

    public String getTargetCluster() {
        return targetCluster;
    }

    public void setTargetCluster(String targetCluster) {
        this.targetCluster = targetCluster;
    }
}
