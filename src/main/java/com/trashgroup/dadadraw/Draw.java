package com.trashgroup.dadadraw;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Draw {

    @RequestMapping("/draw")
    public String draw() {
        return "draw";
    }

}
