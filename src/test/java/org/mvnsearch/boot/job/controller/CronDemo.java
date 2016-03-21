package org.mvnsearch.boot.job.controller;

import org.mvnsearch.spring.boot.job.controller.JobController;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * cron demo
 *
 * @author linux_china
 */
@Component
public class CronDemo extends JobController {

    public String getName() {
        return "demo";
    }

    @Scheduled(initialDelay = 1000, fixedRate = 10000)
    public void demo() {
        String userName = "Jacky" + System.currentTimeMillis();
        run(() -> {
            System.out.println(userName);
        });
    }
}
