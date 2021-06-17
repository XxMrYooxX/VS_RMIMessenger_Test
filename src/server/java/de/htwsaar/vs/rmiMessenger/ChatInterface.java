package de.htwsaar.vs.rmiMessenger;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatInterface extends Remote {
    public void AddMessage(Message message) throws RemoteException;
    public void RemoveMessage(Message message) throws RemoteException;
}
