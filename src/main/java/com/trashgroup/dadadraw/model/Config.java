package com.trashgroup.dadadraw.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class Config {
    private List<Color> colors = new ArrayList<>();
    int currentColorIndex = 0;

    public Config() {
        colors.add(new Color("#4f3610"));
        colors.add(new Color("#1a6314"));
        colors.add(new Color("#408ca8"));
        colors.add(new Color("#e9f013"));
        colors.add(new Color("#f01351"));
        colors.add(new Color("#e8dfe1"));
    }

    public void addColor(Color color) {
        colors.add(color);
    }

    public List<Color> getColors() {
        return Collections.unmodifiableList(colors);
    }

    public void removeColor(Color color) {
        if (colors.size() > 1) {
            colors.remove(color);
        }
    }

    public Color nextColor() {
        if (currentColorIndex >= colors.size()) {
            currentColorIndex = 0;
        }
        return colors.get(currentColorIndex++);

    }
}
