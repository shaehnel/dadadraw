package com.trashgroup.dadadraw.web;


import com.trashgroup.dadadraw.model.Color;
import com.trashgroup.dadadraw.model.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminService {

    @Autowired
    private Config config;

    @RequestMapping(value = "/admin/color/{colorId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteColor(@PathVariable String colorId) {
        config.removeColor(new Color(colorId));
    }

    @RequestMapping(value = "/admin/color", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addColor(@RequestBody Color color) {
        config.addColor(color);
    }
}
