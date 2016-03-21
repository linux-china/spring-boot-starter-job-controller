package org.mvnsearch.spring.boot.job.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.actuate.endpoint.mvc.MvcEndpoint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * job controller endpoint with operations
 *
 * @author linux_china
 */
@RestController
public class JobControllerEndpoint implements MvcEndpoint {
    @Autowired
    private List<JobController> controllers;

    @RequestMapping("/ready")
    @ResponseBody()
    public Map<String, Object> ready(HttpServletRequest request, HttpServletResponse response) {
        for (JobController job : controllers) {
            job.setStatus("ready");
        }
        return Collections.singletonMap("status", "ready");
    }

    @RequestMapping("/pause")
    @ResponseBody()
    public Map<String, Object> pause(HttpServletRequest request, HttpServletResponse response) {
        for (JobController job : controllers) {
            job.setStatus("paused");
        }
        return Collections.singletonMap("status", "paused");
    }

    @RequestMapping("/statics")
    @ResponseBody()
    public List<Map<String, Object>> statics(HttpServletRequest request, HttpServletResponse response) {
        return controllers.stream().map(JobController::getStatics).collect(Collectors.toList());
    }

    public String getPath() {
        return "/schedule";
    }

    public boolean isSensitive() {
        return false;
    }

    public Class<? extends Endpoint> getEndpointType() {
        return null;
    }


}
