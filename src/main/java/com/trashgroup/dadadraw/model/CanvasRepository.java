package com.trashgroup.dadadraw.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CanvasRepository {

    @Autowired
    private Config config;

    private Map<String, Canvas> repository = new HashMap<>();


    public Canvas get(String canvasId) {
        Canvas canvas = repository.get(canvasId);
        if (canvas == null) {
            canvas = new Canvas();
            canvas.setCanvasId(canvasId);
            canvas.setInitialColor(config.nextColor().getHexRGB());
            repository.put(canvasId, canvas);
        }
        return canvas;
    }
}
