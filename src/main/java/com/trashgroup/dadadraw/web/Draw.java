package com.trashgroup.dadadraw.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Draw {

    @RequestMapping("/draw")
    public String draw() {
        return "draw";
    }

    @RequestMapping("/mirror")
    public String mirror() {
        return "mirror";
    }

}
