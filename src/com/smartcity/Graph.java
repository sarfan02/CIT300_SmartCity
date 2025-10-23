package com.smartcity;

import java.util.*;

public class Graph {
    private Map<String, List<String>> adj;
    private Map<String, Location> locations;
    private int nextId;

    public Graph() {
        adj = new HashMap<>();
        locations = new HashMap<>();
        nextId = 1;
    }

    // Add location
    public boolean addLocation(String name) {
        if (adj.containsKey(name)) return false;
        adj.put(name, new ArrayList<>());
        locations.put(name, new Location(name, nextId++));
        return true;
    }

    // Remove location and all edges
    public boolean removeLocation(String name) {
        if (!adj.containsKey(name)) return false;
        adj.remove(name);
        locations.remove(name);
        for (List<String> neighbours : adj.values()) {
            neighbours.remove(name);
        }
        return true;
    }

    // Add undirected road (edge)
    public boolean addRoad(String a, String b) {
        if (!adj.containsKey(a) || !adj.containsKey(b)) return false;
        if (!adj.get(a).contains(b)) adj.get(a).add(b);
        if (!adj.get(b).contains(a)) adj.get(b).add(a);
        return true;
    }

    public boolean removeRoad(String a, String b) {
        if (!adj.containsKey(a) || !adj.containsKey(b)) return false;
        adj.get(a).remove(b);
        adj.get(b).remove(a);
        return true;
    }

    public void displayAllConnections() {
        System.out.println("All connections:");
        for (String key : adj.keySet()) {
            System.out.println(key + " -> " + adj.get(key));
        }
    }

    public void displayAllLocations() {
        System.out.println("All locations:");
        for (Location l : locations.values()) {
            System.out.println(l);
        }
    }

    // BFS using queue from a start node
    public void bfs(String start) {
        if (!adj.containsKey(start)) {
            System.out.println("Start location not found.");
            return;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(start);
        visited.add(start);
        System.out.print("BFS traversal: ");
        while (!q.isEmpty()) {
            String cur = q.poll();
            System.out.print(cur + " ");
            for (String nb : adj.get(cur)) {
                if (!visited.contains(nb)) {
                    visited.add(nb);
                    q.add(nb);
                }
            }
        }
        System.out.println();
    }

    // DFS using stack
    public void dfs(String start) {
        if (!adj.containsKey(start)) {
            System.out.println("Start location not found.");
            return;
        }
        Set<String> visited = new HashSet<>();
        Stack<String> st = new Stack<>();
        st.push(start);
        visited.add(start);
        System.out.print("DFS (stack) traversal: ");
        while (!st.isEmpty()) {
            String cur = st.pop();
            System.out.print(cur + " ");
            for (String nb : adj.get(cur)) {
                if (!visited.contains(nb)) {
                    visited.add(nb);
                    st.push(nb);
                }
            }
        }
        System.out.println();
    }

    // Getter for location object (for AVL or other use)
    public Location getLocation(String name) {
        return locations.get(name);
    }

    // Provide list of names for AVL population
    public List<Location> getAllLocations() {
        return new ArrayList<>(locations.values());
    }
}
