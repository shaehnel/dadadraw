package com.trashgroup.dadadraw.model;

public class Color {
    private String hexRGB;

    public Color() {

    }

    public Color(String hexRGB) {
        this.hexRGB = hexRGB;
    }

    public String getHexRGB() {
        return hexRGB;
    }

    public void setHexRGB(String hexRGB) {
        this.hexRGB = hexRGB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Color color = (Color) o;

        return !(hexRGB != null ? !hexRGB.equals(color.hexRGB) : color.hexRGB != null);

    }

    @Override
    public int hashCode() {
        return hexRGB != null ? hexRGB.hashCode() : 0;
    }
}
