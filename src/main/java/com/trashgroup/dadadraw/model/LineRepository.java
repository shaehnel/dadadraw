package com.trashgroup.dadadraw.model;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class LineRepository {

    private List<Lines> drawings = new ArrayList<>();

    // oh dear, synchronization is a bottleneck
    // either someone is pushing data... or someone is reading data.
    // this is a cheap attempt to not loose any lines due to race conditions
    public synchronized void push(Lines lines) {
        drawings.add(lines);
    }

    public synchronized List<Lines> get() {
        List<Lines> copy = new ArrayList<>(drawings);
        drawings = new ArrayList<>();
        return copy;
    }
}
