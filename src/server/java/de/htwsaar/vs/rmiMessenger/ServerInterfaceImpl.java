package de.htwsaar.vs.rmiMessenger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RMISocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class ServerInterfaceImpl extends UnicastRemoteObject implements ServerInterface{

    private HashMap<ClientInterface, User> clientHashMap;

    /**
     * aktive Chats als ObservableList zur Nutzung von Listener um Aenderungen zu tracken
     */
    private ObservableList<ChatInterfaceImpl> activeChatInterfaces;

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
        activeChatInterfaces = FXCollections.observableArrayList();
        activeChatInterfaces.addListener((ListChangeListener.Change<? extends ChatInterfaceImpl> c) -> {
            while (c.next()) {
                try{
                    if (c.wasAdded()) {
                        loadOnlineChat(c);
                    } else if (c.wasRemoved()) {
                        unloadOnlineChat(c);
                    } else if (c.wasReplaced()) {
                        reloadOnlineChat(c);
                    }
                } catch (RemoteException e) {
                    System.err.println("Handled Error in Serverinterface, don't worry my Friend, es Eschkaliert schon nicht");
                }
            }
        });
    }

    @Override
    public synchronized void connectUser(ClientInterface clientInterface, User user) throws RemoteException {
        User user1 = loadUserFromDB(user);

    }

    @Override
    public synchronized void disconnectUser(ClientInterface clientInterface) throws RemoteException {
        //todo: alles?
    }
    @Override
    public synchronized void addChat(Chat chat) throws RemoteException {
        if(activeChatInterfaces.contains(new ChatInterfaceImpl(chat))){
            activeChatInterfaces.add(new ChatInterfaceImpl(chat));
        }
    }

    public synchronized User loadUserFromDB(User user) throws RemoteException {
        //Todo: implement logic
    }
    private void loadOnlineChat(ListChangeListener.Change<? extends ChatInterfaceImpl> c) throws RemoteException {
        for(int i = c.getFrom(); i < c.getTo(); ++i){ //laufe ueber alle c
            System.out.println("Chat hinzugefuegt:\n" + activeChatInterfaces.get(i).getChat() + "\n");
            for(ClientInterface clientInterface : clientHashMap.keySet()){ //laufe ueber interfaces
                if(activeChatInterfaces.get(i).getChat().getUserList().contains(clientHashMap.get(clientInterface))){
                    try{
                        clientInterface.UpdateChat(activeChatInterfaces.get(i).getChat()); // fuehre aktualisierung aus
                        System.out.println(clientHashMap.get(clientInterface) + "wurde aktualisiert.");
                    } catch (RemoteException e){
                        System.err.println("Aktualisierung des ClientInterface" + clientHashMap.get(clientInterface) + "fehlgeschlagen!");
                        Logger.getLogger(ServerInterface.class.getName()).log(Level.WARNING, null, e);
                    }
                }
            }
        }
    }
    private void unloadOnlineChat(ListChangeListener.Change<? extends ChatInterfaceImpl> c) throws RemoteException {
        for(int i = 0; i < c.getRemovedSize(); i++){ //laufe ueber lediglich entferne inhalte
            System.out.println("Chat entfernt:\n" + c.getRemoved().get(i).getChat());
            for (ClientInterface clientInterface : clientHashMap.keySet()) {
                if(c.getRemoved().get(i).getChat().getUserList().contains(clientHashMap.get(clientInterface))){
                    try{
                        clientInterface.RemoveChat(c.getRemoved().get(i).getChat());
                        System.out.println(clientHashMap.get(clientInterface) + "wurde entfernt und aktualisiert.");
                    } catch (RemoteException e) {
                        System.err.println("Aktualisierung des Clientinterface " +clientHashMap.get(clientInterface) + "fehlgeschlagen. Loeschen abgebrochen.");
                        Logger.getLogger(ServerInterface.class.getName()).log(Level.WARNING, null, e);
                    }
                }
            }
        }
    }
    private void reloadOnlineChat(ListChangeListener.Change<? extends ChatInterfaceImpl> c) throws RemoteException {
        for(int i = c.getFrom(); i < c.getTo(); ++i){ //laufe ueber alle c
            System.out.println("Chat aktualisiert:\n" + activeChatInterfaces.get(i).getChat() + "\n");
            for (ClientInterface clientInterface : clientHashMap.keySet()) {
                if(activeChatInterfaces.get(i).getChat().getUserList().contains(clientHashMap.get(clientInterface))){
                    try {
                        clientInterface.UpdateChat(activeChatInterfaces.get(i).getChat());
                        System.out.println(clientHashMap.get(clientInterface) + "wurde aktualisiert.");
                    } catch (RemoteException e) {
                        System.err.println("Aktualisierung des Clientinterface " +clientHashMap.get(clientInterface) + "fehlgeschlagen.");
                        Logger.getLogger(ServerInterface.class.getName()).log(Level.WARNING, null, e);
                    }
                }
            }
        }
    }
    @Override
    public synchronized User getUser(ClientInterface clientInterface) throws RemoteException { return clientHashMap.get(clientInterface); }
}
