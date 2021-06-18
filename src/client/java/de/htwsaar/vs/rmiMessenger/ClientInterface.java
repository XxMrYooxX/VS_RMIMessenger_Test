package de.htwsaar.vs.rmiMessenger;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    // public void setClientOnline(ClientInterface clientInterface, User User) throws RemoteException; //todo: friend feature? Online status feature?

    // public void setClientDisconnected(ClientInterface clientInterface) throws RemoteException; //todo: friend feature? Online status feature?

    public void AddChat(Chat chat) throws RemoteException;

    public void UpdateChat(Chat chat) throws RemoteException;

    public void RemoveChat(Chat chat) throws RemoteException;

    public User getUser() throws RemoteException;
}
