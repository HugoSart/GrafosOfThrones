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

            System.out.println("Articulation vertices: ");
            for (Vertex v : vAux) {
                System.out.println("\t" + v);
            }

            List<Edge> eAux = graph.bridges();

            System.out.println("Bridges: ");
            for (Edge e : eAux) {
                System.out.println("\t" + e.v1 + ", " + e.v2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
