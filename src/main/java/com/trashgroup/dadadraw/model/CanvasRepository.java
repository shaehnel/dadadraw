package com.trashgroup.dadadraw.model;


import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CanvasRepository {

    private Map<String, Canvas> repository = new HashMap<>();


    public Canvas get(String canvasId) {
        Canvas canvas = repository.get(canvasId);
        if (canvas == null) {
            canvas = new Canvas();
            canvas.setCanvasId(canvasId);
            canvas.setInitialColor("#000");
            repository.put(canvasId, canvas);
        }
        return canvas;
    }
}
