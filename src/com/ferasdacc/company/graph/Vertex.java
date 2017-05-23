package com.ferasdacc.company.graph;

import java.awt.*;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by hugo_ on 22/05/2017.
 */
public class Vertex {

    private int id;
    private String name;

    Color color = Color.WHITE;
    int dist = 2_147_483_647;
    Vertex pred = null;
    int low;
    int f;

    private final SortedSet<Vertex> edges = new TreeSet<>(Comparator.comparing(String::valueOf));

    Vertex(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addEdge(Vertex vertex) {
        edges.add(vertex);
    }

    public boolean hasEdgeWith(Vertex vertex) {
        return edges.contains(vertex);
    }

    public SortedSet<Vertex> edges() {
        return edges;
    }

    public void reset() {
        color = Color.WHITE;
        pred = null;
        dist = 0;
    }

    public void update(Color c, int d, Vertex v) {
        color = c;
        pred = v;
        dist = d;
    }

    public String toString() {
        return name;
    }

}
