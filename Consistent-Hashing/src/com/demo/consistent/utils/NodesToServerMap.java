package com.demo.consistent.utils;

import com.demo.consistent.model.Server;

import java.util.HashMap;
import java.util.Map;

public class NodesToServerMap {
    public static NodesToServerMap INSTANCE = new NodesToServerMap();
    private NodesToServerMap(){

    }
    Map<Server, Server> map = new HashMap<>();

    public void addServerMapping(Server node, Server server){
        map.put(node, server);
    }

    public void removeServerMapping(Server node){
        map.remove(node);
    }

    public Server getActualServer(Server server){
        return map.get(server);
    }
}
