package de.htwsaar.vs.rmiMessenger;

import javafx.collections.ObservableList;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ServerInterfaceImpl extends UnicastRemoteObject implements ServerInterface{

    private HashMap<ClientInterface, Client> clientHashMap;
    private ObservableList<>

    /**
     * Creates and exports a new UnicastRemoteObject object using an
     * anonymous port.
     *
     * <p>The object is exported with a server socket
     * created using the {@link RMISocketFactory} class.
     *
     * @throws RemoteException if failed to export object
     * @since 1.1
     */
    public ServerInterfaceImpl() throws RemoteException {
        clientHashMap = new HashMap<>(); //wird implizit zum richtigen Typ

    }

    @Override
    public synchronized void connectClient(ClientInterface clientInterface, Client client) throws RemoteException {
        Client client1 = LoadClientFromDB(client);

    }

    @Override
    public synchronized void disconnectClient(ClientInterface clientInterface) throws RemoteException {

    }

    @Override
    public synchronized Client getClient(ClientInterface clientInterface) throws RemoteException {
        return clientHashMap.get(clientInterface);
    }

    @Override
    public synchronized void AddChat(Chat chat) throws RemoteException {
        //Todo: implement logic
    }

    public synchronized Client LoadClientFromDB(Client client){
        //Todo: implement logic
    }
}
