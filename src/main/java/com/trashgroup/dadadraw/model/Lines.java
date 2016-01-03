package com.trashgroup.dadadraw.model;

import java.util.*;

public class Lines {

    private String canvasId;
    private boolean clear;
    private String color;
    private java.util.List<Point> points;

    public java.util.List<Point> getPoints() {
        return Collections.unmodifiableList(points);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPoints(java.util.List<Point> points) {
        this.points = points;
    }

    public String getCanvasId() {
        return canvasId;
    }

    public void setCanvasId(String canvasId) {
        this.canvasId = canvasId;
    }

    public boolean isClear() {
        return clear;
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }
}
