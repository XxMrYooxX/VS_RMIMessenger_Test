package de.htwsaar.vs.rmiMessenger;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
    public void connectClient(ClientInterface clientInterface, Client client) throws RemoteException;

    public void disconnectClient(ClientInterface clientInterface) throws RemoteException;

    public Client getClient(ClientInterface clientInterface) throws RemoteException;

    public void AddChat(Chat chat) throws RemoteException;

    public Client LoadClientFromDB(Client client);
}
