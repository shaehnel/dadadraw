package com.trashgroup.dadadraw.web;


import com.trashgroup.dadadraw.model.Canvas;
import com.trashgroup.dadadraw.model.CanvasRepository;
import com.trashgroup.dadadraw.model.LineRepository;
import com.trashgroup.dadadraw.model.Lines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
public class DrawService {

    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private CanvasRepository canvasRepository;

    @RequestMapping(value = "/lines", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void postLines(@RequestBody Lines lines) {
        lineRepository.push(lines);
    }

    @RequestMapping(value = "/lines", method = RequestMethod.GET)
    public @ResponseBody List<Lines> getLines() {
        return lineRepository.get();
    }

    @RequestMapping(value = "/canvas", method = RequestMethod.GET)
    public @ResponseBody Canvas getCanvas(HttpSession httpSession) {
        return canvasRepository.get(httpSession.getId());
    }

}
