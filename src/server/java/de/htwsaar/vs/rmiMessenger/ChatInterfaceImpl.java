package de.htwsaar.vs.rmiMessenger;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatInterfaceImpl extends UnicastRemoteObject implements ChatInterface {
    private Chat chat;
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
    public ChatInterfaceImpl(Chat chat) throws RemoteException {
        super();
        this.chat = chat;
    }

    @Override
    public void AddMessage(Message message) throws RemoteException {
        chat.addMessage(message);
    }

    @Override
    public void RemoveMessage(Message message) throws RemoteException {
        chat.removeMessage(message);
    }

    public void setChat(Chat chat) { this.chat = chat; }
}
