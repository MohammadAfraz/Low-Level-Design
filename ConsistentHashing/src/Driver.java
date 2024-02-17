import com.demo.consistent.model.Server;

import java.util.UUID;

public class Driver {
    public static void main(String args[]){
        Admin<String> admin = new Admin<>();
        //Initialize Consistent Hashing with total hash values
        admin.initializeConsistentHashing(4096);
        //Create servers including virtual nodes
        Server a1 = admin.createServer("A1");
        Server a2 = admin.createServer("A2");
        Server a3 = admin.createServer("A3");
        Server b1 = admin.createServer("B1");
        Server b2 = admin.createServer("B2");
        Server b3 = admin.createServer("B3");
        Server c1 = admin.createServer("C1");
        Server c2 = admin.createServer("C2");
        Server c3 = admin.createServer("C3");
        //Add mapping of virtual nodes to actual server
        admin.addServerMapping(a1, a1);
        admin.addServerMapping(a2, a1);
        admin.addServerMapping(a3, a1);
        admin.addServerMapping(b1, b1);
        admin.addServerMapping(b2, b1);
        admin.addServerMapping(b3, b1);
        admin.addServerMapping(c1, c1);
        admin.addServerMapping(c2, c1);
        admin.addServerMapping(c3, c1);
        //Add servers to the ring
        admin.addServerToRing(a1);
        admin.addServerToRing(a2);
        admin.addServerToRing(a3);
        admin.addServerToRing(b1);
        admin.addServerToRing(b2);
        admin.addServerToRing(b3);
        admin.addServerToRing(c1);
        admin.addServerToRing(c2);
        admin.addServerToRing(c3);
        //Send message to servers using consistent hashing
        for(int i=0; i<50; i++){
            admin.sendRequestToServer(UUID.randomUUID().toString());
        }
    }
}
