package de.htwsaar.vs.rmiMessenger;

import java.rmi.Naming;

public class ServerApp {
    public static void main(String args[]){
        try{
            ServerInterfaceImpl server = new ServerInterfaceImpl();
            Naming.rebind("rmichatserver", server);
            System.out.println("Server ready, waiting for Clients...");
        } catch (Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
