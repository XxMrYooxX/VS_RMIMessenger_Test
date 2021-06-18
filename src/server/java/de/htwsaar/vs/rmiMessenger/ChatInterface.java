package de.htwsaar.vs.rmiMessenger;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatInterface extends Remote {
    public void addMessage(Message message) throws RemoteException;
    public void removeMessage(Message message) throws RemoteException;
}
