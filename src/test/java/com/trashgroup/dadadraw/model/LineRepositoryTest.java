package com.trashgroup.dadadraw.model;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LineRepositoryTest {

    @Test
    public void testReadWrite() throws Exception {
        LineRepository repository =  new LineRepository();
        Lines lines = generateLines("black", 5);
        repository.push(lines);
        final List<Lines> retrieved = repository.get();
        Assert.assertEquals(lines.getColor(), retrieved.get(0).getColor());
    }


    @Test
    public void testConcurrentReadWrite() throws Exception {
        LineRepository repository =  new LineRepository();
        Lines lines1 = generateLines("first", 5);
        repository.push(lines1);
        Lines lines2 = generateLines("second", 7);
        repository.push(lines2);

        final List<Lines> retrieved1 = repository.get();

        Assert.assertEquals(2, retrieved1.size());
        Assert.assertEquals("first", retrieved1.get(0).getColor());

        Lines lines3 = generateLines("third", 3);
        repository.push(lines3);

        final List<Lines> retrieved2 = repository.get();
        Assert.assertEquals(1, retrieved2.size());
        Assert.assertEquals("third", retrieved2.get(0).getColor());
    }


    private Lines generateLines(String color, int numberOfPoints) {
        Lines lines = new Lines();
        lines.setColor(color);
        List<Point> points = new ArrayList<>(numberOfPoints);
        for (int i=0; i<numberOfPoints; i++) {
            Point p = new Point();
            p.setX(i);
            p.setY(i);
            points.add(p);
        }
        lines.setPoints(points);
        return lines;
    }
}