package com.vaadin.demo.service;

/**
 * Created by devarshdesai on 3/5/17.
 */
public class LongStringPair {
    private Long count;
    private String group;


    public LongStringPair(Long count, String group) {
        this.count = count;
        this.group = group;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
