package de.htwsaar.vs.rmiMessenger;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    public void setClientOnline(ClientInterface clientInterface, Client client) throws RemoteException;

    public void setClientDisconnected(ClientInterface clientInterface) throws RemoteException;

    public void AddChat(Chat chat) throws RemoteException;

    public void UpdateChat(Chat chat) throws RemoteException;

    public void RemoveChat(Chat chat) throws RemoteException;

    public Client getClient() throws RemoteException;
}
