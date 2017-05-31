package com.ferasdacc.company.graph;

import com.opencsv.CSVReader;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

/**
 * Created by hugo_ on 22/05/2017.
 */
public class Graph {

    private int size = 0;

    private int time = 0;

    private SortedSet<Vertex> vertices = new TreeSet<Vertex>(Comparator.comparing(String::valueOf));

    public static Graph fromCSV(String path) throws IOException {

        Graph graph = null;
        CSVReader reader = null;

        try {
            reader = new CSVReader(new FileReader(path));
            graph = new Graph();

            String[] line;
            while(((line = reader.readNext()) != null)) {
                graph.newVertex(line[0]).addEdge(graph.newVertex(line[1]));
                graph.newVertex(line[1]).addEdge(graph.newVertex(line[0]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;

    }

    public SortedSet<Vertex> articulationVertices() {
        time = 0;
        SortedSet<Vertex> vAux = new TreeSet<Vertex>(Comparator.comparing(String::valueOf));
        articulationVertices_aux(vertices.first(), vAux);
        reset();
        return vAux;
    }

    private void articulationVertices_aux(Vertex u, SortedSet<Vertex> vAux) {
        time++;
        u.color = Color.GRAY;
        u.low = u.d = time;

        for (Vertex v : u.edges()) {
            if (v.color == Color.WHITE) {
                v.pred = u;
                articulationVertices_aux(v, vAux);

                if (u.pred == null) {
                    if (u.edges().contains(v) && u.edges().size() >= 2) {
                        vAux.add(u);
                    }
                } else {
                    u.low = Math.min(u.low, v.low);
                    if (v.low >= u.d) {
                        vAux.add(u);
                    }
                }

            } else {
                if (v != u.pred && v.d < u.d) {
                    u.low = Math.min(u.low, v.d);
                }
            }
        }
        u.color = Color.BLACK;
        time++;
        u.f = time;

    }

    public List<Edge> bridges() {
        time = 0;
        List<Edge> vAux = new ArrayList<>();
        bridges_aux(vertices.last(), vAux);
        reset();
        return vAux;
    }

    private void bridges_aux(Vertex u, List<Edge> edges) {
        time++;
        u.color = Color.GRAY;
        u.low = u.d = time;

        for (Vertex v : u.edges()) {
            if (v.color == Color.WHITE) {
                v.pred = u;
                bridges_aux(v, edges);
                u.low = Math.min(u.low, v.low);

                if (v.low > u.d)
                    edges.add(new Edge(u, v));

            } else {
                if (v != u.pred && v.d < u.d)
                    u.low = Math.min(u.low, v.d);
            }
        }

        u.color = Color.BLACK;
        time++;
        u.f = time;

    }

    public void printVertices() {

        int i = 0;

        System.out.print("Vertices[" + size() + "] = { ");
        for (Vertex v : vertices) {
            System.out.print(v.toString() + " ");
            if (i == 20) {
                System.out.print("\n                 ");
                i = 0;
            }
            i++;
        }
        System.out.println("}");

    }

    public void printVerticesEdges() {

        System.out.print("Vertices[" + size() + "] = {\n");
        for (Vertex v : vertices) {
            System.out.print("\t" + v.toString());

            SortedSet<Vertex> edges = v.edges();

            System.out.print("\t => { ");

            for (Vertex e : edges) {
                System.out.print(e.toString() + " ");
            }

            System.out.print("}\n");
        }
        System.out.println("}");

    }

    private Vertex newVertex(String name) {

        for (Vertex v : vertices) {
            if (v.toString().equals(name)) {
                return v;
            }
        }

        Vertex v = new Vertex(size++, name);
        vertices.add(v);
        return v;

    }

    public int size() {
        return size;
    }

    public Vertex getVertex(String name) {

        for (Vertex v : vertices) {
            if (v.toString().equals(name))
                return v;
        }

        return null;

    }

    public int distanceBetwen(Vertex v1, Vertex v2) {

        v1.update(Color.GRAY, 0, null);

        Stack<Vertex> q = new Stack<>();

        q.push(v1);

        while(!q.isEmpty()) {

            Vertex u = q.pop();

            for (Vertex v : u.edges()) {
                if (v.color == Color.WHITE) {
                    v.update(Color.GRAY, u.dist + 1, u);
                    q.push(v);
                }
            }

            u.color = Color.BLACK;

        }

        int dist = v2.dist;

        printAux(v1, v2);

        reset();

        return dist;

    }

    private void printAux(Vertex v1, Vertex v2) {
        if (v1 == v2)
            System.out.print(v1 + " ");
        else
            if (v2.pred == null) System.out.print("Não existe caminho.");
            else {
                printAux(v1, v2.pred);
                System.out.print(v2 + " ");
            }
    }

    public int distanceBetwen(String s1, String s2) {
        return distanceBetwen(getVertex(s1), getVertex(s2));
    }

    public void reset() {

        for (Vertex v : vertices) {
            v.reset();
        }

    }

}
