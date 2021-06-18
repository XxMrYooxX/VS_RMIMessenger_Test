package de.htwsaar.vs.rmiMessenger;

import java.io.Serializable;
import java.util.ArrayList;

public class Chat implements Serializable {
    private int id;
    private String name;
    private long creationDate;

    private ArrayList<User> userList;
    private ArrayList<Message> messageList;

    public Chat(String name){
        this.name = name;
        this.creationDate = (System.currentTimeMillis() / 1000);
        this.id = registerChat(this.name, this.creationDate);
        this.userList = new ArrayList<>();
        this.messageList = new ArrayList<>();
    }
    public Chat(int id){
        //todo: logic pls fuer Konstruktor der vorhandenen Chat abruft
    }

    public void addMessage(Message message){
        messageList.add(message);
    }

    public void removeMessage(Message message){
        messageList.remove(message);
    }

    public int registerChat(String name, long creationDate){
        //todo: needs db logic
        int id = 0; //todo: vorsicht, muss bearbeitet werden
        return id;
    }

    public void unregisterChat(int chatid) {
        //todo: needs db logic
    }

    public void addUser(User user){
        userList.add(user);
    }

    public void removeUser(User user){
        userList.remove(user);
    }

    @Override
    public String toString() {
        return "Teilnehmer:\n" + userList + "\n\n\n" + "Chat:\n" + messageList;
    }

    public int getId() {
        return id;
    }
    public String getName(){
        return name;
    }
    public long getCreationDate(){
        return creationDate;
    }
    public ArrayList<User> getUserList() { return userList; }
    public ArrayList<Message> getMessageList() { return messageList; }

}
