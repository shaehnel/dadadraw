package com.trashgroup.dadadraw.web;


import com.trashgroup.dadadraw.model.LineRepository;
import com.trashgroup.dadadraw.model.Lines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class LineService {

    @Autowired
    private LineRepository lineRepository;

    @RequestMapping(value = "/lines", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void postLines(@RequestBody Lines lines) {
        lineRepository.push(lines);
    }

    @RequestMapping(value = "/lines", method = RequestMethod.GET)
    public @ResponseBody List<Lines> getLines() {
        return lineRepository.get();
    }

}
