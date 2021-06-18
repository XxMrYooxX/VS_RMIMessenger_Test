package de.htwsaar.vs.rmiMessenger;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
    public void connectUser(ClientInterface clientInterface, User user) throws RemoteException;

    public void disconnectUser(ClientInterface clientInterface) throws RemoteException;

    public User getUser(ClientInterface clientInterface) throws RemoteException;

    public void addChat(Chat chat) throws RemoteException;

    public User loadUserFromDB(User user) throws RemoteException;
}
