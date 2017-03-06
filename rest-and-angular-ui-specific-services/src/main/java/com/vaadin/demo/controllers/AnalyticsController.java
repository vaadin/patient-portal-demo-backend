package com.vaadin.demo.controllers;

import com.vaadin.demo.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    AnalyticsService analyticsService;

    @RequestMapping(path = "/age", method = RequestMethod.GET)
    public Map<String, Object> getStatsByAge() {
        return result("age", analyticsService.getStatsByAge());
    }

    @RequestMapping(path = "/gender", method = RequestMethod.GET)
    public Map<String, Object> getStatsByGender() {
        return result("gender", analyticsService.getStatsByGender());
    }

    @RequestMapping(path = "/doctor", method = RequestMethod.GET)
    public Map<String, Object> getStatsByDoctor() {
        return result("doctor", analyticsService.getStatsByDoctor());
    }

    private HashMap<String, Object> result(String group, List<HashMap<String, Object>> list) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("grouping", group);
        result.put("data", list);
        return result;
    }

}