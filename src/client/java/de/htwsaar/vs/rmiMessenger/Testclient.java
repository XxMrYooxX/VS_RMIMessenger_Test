package de.htwsaar.vs.rmiMessenger;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Testclient {
    private Testclient() {}

    public static void main(String[] args){
        String host = "localhost";
        try{
            Registry registry = LocateRegistry.getRegistry(host, 42424);


        } catch (Exception e){
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
