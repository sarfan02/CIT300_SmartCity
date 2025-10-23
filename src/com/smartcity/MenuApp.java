package com.smartcity;

import java.util.Scanner;

public class MenuApp {
    private static Graph graph = new Graph();
    private static AVLTree avl = new AVLTree();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // sample initial data
        graph.addLocation("A");
        graph.addLocation("B");
        graph.addLocation("C");
        graph.addRoad("A", "B");
        graph.addRoad("B", "C");

        // populate AVL
        for (Location loc : graph.getAllLocations()) {
            avl.insert(loc.name, loc);
        }

        while (true) {
            printMenu();
            int choice = Utils.readInt(sc, "Enter your choice: ");
            switch (choice) {
                case 1:
                    addLocation();
                    break;
                case 2:
                    removeLocation();
                    break;
                case 3:
                    addRoad();
                    break;
                case 4:
                    removeRoad();
                    break;
                case 5:
                    graph.displayAllConnections();
                    break;
                case 6:
                    // show using tree
                    avl.printInOrder();
                    break;
                case 7:
                    // extra: BFS or DFS demo
                    String start = Utils.readString(sc, "Enter start node for BFS/DFS (or blank to skip): ");
                    if (!start.isBlank()) {
                        graph.bfs(start);
                        graph.dfs(start);
                    }
                    break;
                case 8:
                    System.out.println("Exiting. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("--- Smart City Route Planner ---");
        System.out.println("1. Add a new location");
        System.out.println("2. Remove a location");
        System.out.println("3. Add a road between locations");
        System.out.println("4. Remove a road");
        System.out.println("5. Display all connections");
        System.out.println("6. Display all locations (AVL tree)");
        System.out.println("7. BFS & DFS demo (queue / stack)");
        System.out.println("8. Exit");
    }

    private static void addLocation() {
        String name = Utils.readString(sc, "Enter new location name: ");
        if (graph.addLocation(name)) {
            avl.insert(name, graph.getLocation(name));
            System.out.println("Location added: " + name);
        } else System.out.println("Already exists.");
    }

    private static void removeLocation() {
        String name = Utils.readString(sc, "Enter location name to remove: ");
        if (graph.removeLocation(name)) {
            System.out.println("Removed location: " + name);
            // Note: AVL deletion not implemented in this short example. You can rebuild AVL:
            rebuildAVL();
        } else System.out.println("Not found.");
    }

    private static void addRoad() {
        String a = Utils.readString(sc, "Enter first location: ");
        String b = Utils.readString(sc, "Enter second location: ");
        if (graph.addRoad(a, b)) System.out.println("Road added between " + a + " and " + b);
        else System.out.println("One or both locations not found.");
    }

    private static void removeRoad() {
        String a = Utils.readString(sc, "Enter first location: ");
        String b = Utils.readString(sc, "Enter second location: ");
        if (graph.removeRoad(a, b)) System.out.println("Road removed between " + a + " and " + b);
        else System.out.println("One or both locations not found.");
    }

    private static void rebuildAVL() {
        avl = new AVLTree();
        for (Location l : graph.getAllLocations()) avl.insert(l.name, l);
    }
}
