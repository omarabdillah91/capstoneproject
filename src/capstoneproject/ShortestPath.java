/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstoneproject;

import static capstoneproject.CapstoneProject.records;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author omaabdillah
 */
public class ShortestPath {

    static Hashtable<String, Integer> index_bus = new Hashtable<String, Integer>();
    static private int distances[];
    static private Set<Integer> settled;
    static private Set<Integer> unsettled;
    static private int number_of_nodes;
    static private int adjacencyMatrix[][];

    public ShortestPath(int number_of_nodes) {

        this.number_of_nodes = number_of_nodes;
        distances = new int[number_of_nodes + 1];
        settled = new HashSet<Integer>();
        unsettled = new HashSet<Integer>();
        adjacencyMatrix = new int[number_of_nodes + 1][number_of_nodes + 1];

    }

    public void dijkstra_algorithm(int adjacency_matrix[][], int source) {

        int evaluationNode;
        for (int i = 1; i <= number_of_nodes; i++) {
            for (int j = 1; j <= number_of_nodes; j++) {
                adjacencyMatrix[i][j] = adjacency_matrix[i][j];
            }
        }
        for (int i = 1; i <= number_of_nodes; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        unsettled.add(source);
        distances[source] = 0;
        while (!unsettled.isEmpty()) {
            evaluationNode = getNodeWithMinimumDistanceFromUnsettled();
            unsettled.remove(evaluationNode);
            settled.add(evaluationNode);
            evaluateNeighbours(evaluationNode);
        }

    }

    private int getNodeWithMinimumDistanceFromUnsettled() {
        int min;
        int node = 0;
        Iterator<Integer> iterator = unsettled.iterator();
        node = iterator.next();
        min = distances[node];
        for (int i = 1; i <= distances.length; i++) {
            if (unsettled.contains(i)) {
                if (distances[i] <= min) {
                    min = distances[i];
                    node = i;
                }
            }
        }
        return node;

    }

    private void evaluateNeighbours(int evaluationNode) {
        int edgeDistance = -1;
        int newDistance = -1;
        for (int destinationNode = 1; destinationNode <= number_of_nodes; destinationNode++) {
            if (!settled.contains(destinationNode)) {
                if (adjacencyMatrix[evaluationNode][destinationNode] != Integer.MAX_VALUE) {
                    edgeDistance = adjacencyMatrix[evaluationNode][destinationNode];
                    newDistance = distances[evaluationNode] + edgeDistance;
                    if (newDistance < distances[destinationNode]) {
                        distances[destinationNode] = newDistance;
                    }
                    unsettled.add(destinationNode);
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        String trayek_bus = "/home/omaabdillah/Documents/DATA SCIENCE INDONESIA/rute_angkutan_umum.csv";
        BufferedReader br = null;
        String line = "";
        String splitter = ",";
        br = new BufferedReader(new FileReader(trayek_bus));
        br.readLine();
        int indeks = 1;
        while ((line = br.readLine()) != null) {
            // use comma as separator
            String[] country = line.split(splitter);
            String[] rute = country[0].split(" -- ");
            for (int i = 0; i < rute.length; i++) {
                if (rute[i] != "") {
                    if (!index_bus.containsKey(rute[i])) {
                        index_bus.put(rute[i], indeks);
                        indeks++;
                    }
                }
            }
            rute = country[1].split(" -- ");
            for (int i = 0; i < rute.length; i++) {
                if (rute[i] != "") {
                    if (!index_bus.containsKey(rute[i])) {
                        index_bus.put(rute[i], indeks);
                        indeks++;
                    }
                }
            }
        }

        int adjacency_matrix[][];
        int number_of_vertices;
        int source = 0, destination = 0;
        number_of_vertices = index_bus.size();
        System.out.println(number_of_vertices);
        adjacency_matrix = new int[number_of_vertices + 1][number_of_vertices + 1];

        br = new BufferedReader(new FileReader(trayek_bus));
        br.readLine();
        while ((line = br.readLine()) != null) {
            // use comma as separator
            String[] country = line.split(splitter);
            String[] rute = country[0].split(" -- ");
            String village = "";
            for (int i = 0; i < rute.length; i++) {
                village = rute[i];
                int ind_i = index_bus.get(village);
                adjacency_matrix[ind_i][ind_i] = 0;
                if (i < rute.length - 1) {
                    for (int j = i + 1; j < rute.length; j++) {
                        village = rute[j];
                        int ind_j = index_bus.get(village);
                        adjacency_matrix[ind_i][ind_j] = 1;
                        adjacency_matrix[ind_j][ind_i] = 1;
                    }
                }
            }
        }

        for (int i = 1; i <= number_of_vertices; i++) {
            for (int j = 1; j <= number_of_vertices; j++) {
                if (i == j) {
                    continue;
                }
                if (adjacency_matrix[i][j] == 0) {
                    adjacency_matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        String[] macet = new String[]{"gelora", "tanah abang", "kebon sirih", "pasar baru", "bendungan hilir", "gandaria selatan", "harapan mulia", "cikini", "utan kayu selatan", "senayan", "setiabudi", "karet", "karet semanggi", "karet kuningan", "kuningan timur", "menteng atas", "glodok"};
        Hashtable<String, Double> macet_route = new Hashtable<String, Double>();
        for (int i = 0; i < macet.length; i++) {
            destination = index_bus.get(macet[i]);
            int ind = 0;
            int hasil = 0;
            for (String s : index_bus.keySet()) {
                source = index_bus.get(s);
                ShortestPath dijkstrasAlgorithm = new ShortestPath(number_of_vertices);
                dijkstrasAlgorithm.dijkstra_algorithm(adjacency_matrix, source);
                for (int j = 1; j <= dijkstrasAlgorithm.distances.length - 1; j++) {
                    if (j == destination) {
                        if (dijkstrasAlgorithm.distances[j] < Integer.MAX_VALUE) {
                            hasil += dijkstrasAlgorithm.distances[j];
                            ind++;
                        }
                    }
                }
            }
            System.out.println(hasil + " " + ind);
            macet_route.put(macet[i], (double) (hasil / ind));
        }

        for (String s : macet_route.keySet()) {
            System.out.println(s + " " + macet_route.get(s));
        }
    }
}
