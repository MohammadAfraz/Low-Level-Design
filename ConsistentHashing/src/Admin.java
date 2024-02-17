import com.demo.consistent.exceptions.ServerNotFoundException;
import com.demo.consistent.model.Server;
import com.demo.consistent.utils.NodesToServerMap;

import java.util.List;
import java.util.UUID;

public class Admin<Key> {
    private ConsistentHashing consistentHashing;
    private NodesToServerMap nodesToServerMap = NodesToServerMap.INSTANCE;

    public void initializeConsistentHashing(int totalHashValues){
        consistentHashing = new ConsistentHashing(totalHashValues);
    }

    public Server createServer(String serverName){
        return new Server(UUID.randomUUID().toString(), serverName,null);
    }

    public void addServerToRing(Server server){
        consistentHashing.addServer(server);
    }

    public void removeServerFromRing(Server server){
        consistentHashing.removeServer(server);
        removeServerMapping(server);
    }

    public void addServerMapping(Server virtualNode, Server server){
        nodesToServerMap.addServerMapping(virtualNode, server);
    }

    public void addServerMapping(List<Server> virtualNode, Server server){
        virtualNode.stream().forEach(node -> addServerMapping(node, server));
    }

    public void removeServerMapping(Server node){
        nodesToServerMap.removeServerMapping(node);
    }

    private Server findServer(Key key){
        return consistentHashing.findServer(key);
    }

    public void sendRequestToServer(Key key){
        Server server = findServer(key);
        Server actualServer = nodesToServerMap.getActualServer(server);
        if(actualServer == null){
            throw new ServerNotFoundException("Server not found in ring!!");
        }
        actualServer.processRequest(key);
    }

}
