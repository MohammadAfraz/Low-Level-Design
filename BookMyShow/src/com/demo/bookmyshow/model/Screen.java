package com.demo.bookmyshow.model;

import java.util.Map;

public class Screen {
    String name;
    int capacity;
    Map<String, Seat> seatsMap;

    public Screen(String screenName, int capacity) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Map<String, Seat> getSeatsMap() {
        return seatsMap;
    }

    public void setSeatsMap(Map<String, Seat> seatsMap) {
        this.seatsMap = seatsMap;
    }
}
