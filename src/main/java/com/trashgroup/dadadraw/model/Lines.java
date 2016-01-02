package com.trashgroup.dadadraw.model;

import java.util.*;

public class Lines {

    private String color;

    public java.util.List<Point> getPoints() {
        return Collections.unmodifiableList(points);
    }

    public String getColor() {
        return color;
    }

    private java.util.List<Point> points;

    public void setColor(String color) {
        this.color = color;
    }

    public void setPoints(java.util.List<Point> points) {
        this.points = points;
    }
}