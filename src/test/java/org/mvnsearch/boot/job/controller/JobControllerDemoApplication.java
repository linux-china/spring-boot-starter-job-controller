package org.mvnsearch.boot.job.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * job controller demo application
 *
 * @author linux_china
 */
@EnableScheduling
@SpringBootApplication
public class JobControllerDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobControllerDemoApplication.class, args);
    }
}
