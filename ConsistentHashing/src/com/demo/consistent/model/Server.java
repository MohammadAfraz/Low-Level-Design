package com.demo.consistent.model;

public class Server {
    String serverId;
    String serverName;
    String serverIp;

    public Server(String serverId, String serverName, String serverIp) {
        this.serverId = serverId;
        this.serverName = serverName;
        this.serverIp = serverIp;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }
    public void processRequest(Object object){
        System.out.println("Server "+serverName+", Processing Request "+ object.toString());
    }
}
