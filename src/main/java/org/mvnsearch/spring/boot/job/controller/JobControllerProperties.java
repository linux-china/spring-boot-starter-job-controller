package org.mvnsearch.spring.boot.job.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * job controller properties
 *
 * @author linux_china
 */
@ConfigurationProperties(prefix = "spring.mybatis")
public class JobControllerProperties {
    /**
     * auto start
     */
    private boolean autoStart = false;

    public boolean isAutoStart() {
        return autoStart;
    }

    public void setAutoStart(boolean autoStart) {
        this.autoStart = autoStart;
    }
}
