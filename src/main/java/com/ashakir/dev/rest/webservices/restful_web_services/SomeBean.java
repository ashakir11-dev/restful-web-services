package com.ashakir.dev.rest.webservices.restful_web_services;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties("s2")
@JsonFilter("filter")
public class SomeBean {
//    @JsonIgnore
    private String s1;
    private String s2;
    private String s;

    public SomeBean(String s1, String s2, String s) {
        this.s1 = s1;
        this.s2 = s2;
        this.s = s;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s) {
        this.s2 = s;
    }
}
