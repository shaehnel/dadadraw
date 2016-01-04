package com.trashgroup.dadadraw.web;


import com.trashgroup.dadadraw.model.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Admin {

    @Autowired
    private Config config;

    @RequestMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("colors", config.getColors());
        return "admin";
    }
}
