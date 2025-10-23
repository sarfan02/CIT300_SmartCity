package com.smartcity;

public class Location {
    public String name;
    public int id; // optional numeric id

    public Location(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return name + " (ID:" + id + ")";
    }
}
