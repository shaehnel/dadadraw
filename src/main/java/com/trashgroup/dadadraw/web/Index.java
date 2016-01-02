package com.trashgroup.dadadraw.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
public class Index {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
