package com.ashakir.dev.rest.webservices.restful_web_services.helloworld;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    @GetMapping(path="/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path="/hello-world-bean")
    public HelloWorldBean hellowWorldBean(){
        return new HelloWorldBean("Hello World");
    }
    @GetMapping(path="/hello-world-dynamic/{fname}/{lname}")
    public HelloWorldBean helloWorldDynamic(@PathVariable String fname, @PathVariable String lname){
        return new HelloWorldBean("Hello World " + fname + " " + lname);
    }

}
