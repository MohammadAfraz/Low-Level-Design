package com.demo.bookmyshow.model;

import com.demo.bookmyshow.model.Address;

import java.util.Map;

public class Cinema {
    String name;
    Address address;
    Map<String, Screen> screensMap;
    Map<String, Show> showsMap;

    public Cinema(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Map<String, Screen> getScreensMap() {
        return screensMap;
    }

    public void setScreensMap(Map<String, Screen> screensMap) {
        this.screensMap = screensMap;
    }

    public Map<String, Show> getShowsMap() {
        return showsMap;
    }

    public void setShowsMap(Map<String, Show> showsMap) {
        this.showsMap = showsMap;
    }
}
