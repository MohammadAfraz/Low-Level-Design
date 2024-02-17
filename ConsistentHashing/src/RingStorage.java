import com.demo.consistent.model.Server;

import java.util.Map;
import java.util.TreeMap;

public class RingStorage {
    public static RingStorage INSTANCE = new RingStorage();
    TreeMap<Integer, Server> serverMap;
    private RingStorage(){
        serverMap = new TreeMap<>();
    }

    public void addServer(Integer hashValue, Server server){
        serverMap.put(hashValue, server);
    }

    public void removeServer(Integer hashValue){
        serverMap.remove(hashValue);
    }

    public void removeServer(Server server){
        serverMap.remove(server);
    }

    public Server findServer(Integer hashValue){
        Map.Entry<Integer, Server> serverEntry = serverMap.ceilingEntry(hashValue);
        if(serverEntry == null){
            serverEntry = serverMap.ceilingEntry(0);
        }
        return serverEntry.getValue();
    }
}
