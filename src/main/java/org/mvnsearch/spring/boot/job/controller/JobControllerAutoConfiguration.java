/*
 *    Copyright 2010-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.mvnsearch.spring.boot.job.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * job controller auto configuration
 *
 * @author linux_china
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableConfigurationProperties(JobControllerProperties.class)
public class JobControllerAutoConfiguration {
    @Autowired
    private JobControllerProperties properties;
    @Autowired(required = false)
    private List<JobController> controllers;

    @PostConstruct
    public void checkConfigFileExists() {
        if (properties.isAutoStart()) {
            for (JobController controller : controllers) {
                controller.setStatus("ready");
            }
        }
    }

    @Bean
    public JobControllerEndpoint jobControllerEndpoint() {
        return new JobControllerEndpoint();
    }

}
