package com.demo.consistent.model;

import com.demo.consistent.model.Server;

public interface ConsistentHashingInterface<Key> {
    public int addServer(Server node);

    public void removeServer(Server node);

    public Server findServer(Key key);
}
