package com.ferasdacc.company;

import com.ferasdacc.company.graph.Graph;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Graph graph = null;

        try {
            graph = Graph.fromCSV("stormofswords.csv");
            graph.printVerticesEdges();
            System.out.println("Distance: " + graph.distanceBetwen("ELIA", "LOTHAR"));
            graph.getVertex("ARYA");
            graph.printArticulationVertices();
            graph.printBridges();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
