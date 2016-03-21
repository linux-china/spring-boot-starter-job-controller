package org.mvnsearch.spring.boot.job.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * job controller
 *
 * @author linux_china
 */
public abstract class JobController {
    private int succeedCount = 0;
    private String status = "paused";
    private Date lastExecutedTime;
    private Long duration;
    private Exception lastException;

    public abstract String getName();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastExecutedTime() {
        return lastExecutedTime;
    }

    public Long getDuration() {
        return duration;
    }

    public int getSucceedCount() {
        return succeedCount;
    }

    public void setSucceedCount(int succeedCount) {
        this.succeedCount = succeedCount;
    }

    public void run(Runnable runnable) {
        if (Objects.equals(status, "ready") || Objects.equals(status, "finished")) {
            try {
                lastExecutedTime = new Date();
                long start = System.currentTimeMillis();
                runnable.run();
                duration = System.currentTimeMillis() - start;
                lastException = null;
                succeedCount++;
            } catch (Exception e) {
                lastException = e;
            }
        }
    }

    public Map<String, Object> getStatics() {
        Map<String, Object> statics = new HashMap<>();
        statics.put("status", status);
        statics.put("name", getName());
        statics.put("succeedCount", succeedCount);
        if (lastExecutedTime != null) {
            statics.put("lastExecutedTime", lastExecutedTime);
            statics.put("lastExecutedTimeText", lastExecutedTime.toString());
            statics.put("duration", duration);
            if (lastException != null) {
                final StringWriter sw = new StringWriter();
                final PrintWriter pw = new PrintWriter(sw, true);
                lastException.printStackTrace(pw);
                statics.put("lastException", sw.toString());
            }
        }
        return statics;
    }
}
