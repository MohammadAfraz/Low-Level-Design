import com.demo.consistent.model.ConsistentHashingInterface;
import com.demo.consistent.model.Server;

public class ConsistentHashing<Key> implements ConsistentHashingInterface {
    private int totalHashValues;
    private RingStorage ringStorage = RingStorage.INSTANCE;

    public ConsistentHashing(int totalHashValues){
        this.totalHashValues = totalHashValues;
    }

    public int addServer(Server node){
        int hashValue = generateHash(node);
        ringStorage.addServer(hashValue, node);
        return hashValue;
    }

    private int generateHash(Server node){
        int hashCode = Math.abs(node.getServerId().hashCode());
        return hashCode % totalHashValues;
    }
    public void removeServer(Server node){
        int hashValue = generateHash(node);
        ringStorage.removeServer(hashValue);
    }

    public Server findServer(Object key){
        int hashCode = Math.abs(key.hashCode());
        int hashValue = hashCode % totalHashValues;
        return ringStorage.findServer(hashValue);
    }
}
