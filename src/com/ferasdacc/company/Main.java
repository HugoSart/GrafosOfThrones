package com.ferasdacc.company;

import com.ferasdacc.company.graph.Edge;
import com.ferasdacc.company.graph.Graph;
import com.ferasdacc.company.graph.Vertex;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;

public class Main {

    public static void main(String[] args) {

        Graph graph = null;

        try {
            graph = Graph.fromCSV("stormofswords.csv");
            graph.printVerticesEdges();
            System.out.println("Distance: " + graph.distanceBetwen("ELIA", "LOTHAR"));

            SortedSet<Vertex> vAux = graph.articulationVertices();

            System.out.println("Articulation vertices (" + vAux.size() + "): ");
            for (Vertex v : vAux) {
                System.out.println("\t" + v);
            }

            List<Edge> eAux = graph.bridges();

            System.out.println("Bridges (" + eAux.size() + "): ");
            for (Edge e : eAux) {
                System.out.println("\t" + e.v1 + ", " + e.v2);
            }

            System.out.println("Minimo: " + graph.distanceBetwen("STANNIS", "DAVOS"));
            System.out.println("Minimo: " + graph.distanceBetwen("ARYA", "STANNIS"));
            System.out.println("Minimo: " + graph.distanceBetwen("JON", "MERYN"));
            System.out.println("Minimo: " + graph.distanceBetwen("ELIA", "LOTHAR"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
