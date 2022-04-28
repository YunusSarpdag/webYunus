package com.yunus.web.webyunus;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(path = "hello-world")
    public String helloWord(){
        return "Hello World";
    }

    @GetMapping(path = "hello-world-bean")
    public HelloWorldBean helloWordBean(){
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "hello-world/tt/{name}")
    public HelloWorldBean helloWordBean(@PathVariable String name){
        return new HelloWorldBean(name);
    }
}
