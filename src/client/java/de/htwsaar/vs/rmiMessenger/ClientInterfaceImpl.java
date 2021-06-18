package de.htwsaar.vs.rmiMessenger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

import java.lang.management.PlatformLoggingMXBean;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientInterfaceImpl extends UnicastRemoteObject implements ClientInterface {
    private ServerInterface serverInterface;
    private ObservableList<Chat> chats;
    private User user;

    private Pane chatPane; //todo: @frontendTeam einmal diese richtig in die fxml und so implementieren

    public ClientInterfaceImpl() throws RemoteException, NotBoundException, MalformedURLException{
        this.serverInterface = (ServerInterface) Naming.lookup("rmichatserver");
        this.chats = FXCollections.observableArrayList();
    }

    @Override
    public void AddChat(Chat chat) throws RemoteException {
        Platform.runLater(()-> {    // fuehrt Code zur unbestimmten Zeit als Thread aus aka. pending runable
            chats.add(chat);
        });
    }

    @Override
    public void UpdateChat(Chat chat) throws RemoteException {
        if(chats.contains(chat)){
            Platform.runLater(() -> {   // fuehrt Code zur unbestimmten Zeit als Thread aus aka. pending runable
                chats.set(chats.indexOf(chat), chat);
            });
        }
    }

    @Override
    public void RemoveChat(Chat chat) throws RemoteException {
        if(chats.contains(chat)){
            Platform.runLater(() -> {    // fuehrt Code zur unbestimmten Zeit als Thread aus aka. pending runable
                chats.remove(chat);
            });
        }
    }

    @Override
    public User getUser() throws RemoteException { return user; }
    public void setUser(User user) throws RemoteException{ this.user = user; }
    public Pane getChatPane() { return chatPane; }
    public void setChatPane(Pane chatPane) { this.chatPane = chatPane; }
}
