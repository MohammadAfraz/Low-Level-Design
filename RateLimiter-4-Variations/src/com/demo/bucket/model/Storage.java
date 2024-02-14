package com.demo.bucket.model;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Storage {
    Deque<Request> requests;
    public static Storage INSTANCE = new Storage();
    public Storage(){
        requests = new ConcurrentLinkedDeque<>();
    }
    public void addRequest(Request request){
        requests.addLast(request);
    }
}
