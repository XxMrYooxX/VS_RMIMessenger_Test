package de.htwsaar.vs.rmiMessenger;

import java.io.Serializable;

public class Message implements Serializable {

    private User sender;
    private String payload;
    private int id;
    private Chat chat;
    private long creationTime;

    public Message(Chat chat, User sender, String payload){
        this.sender = sender;
        this.payload = payload;
        this.chat = chat;
        this.id = registerMessage(this.sender, this.chat, this.payload);
    }

    public Message(int id){
        //todo: some logic :/ brauchen die message anhand der id zum createn des obj
    }

    public int registerMessage(User sender, Chat chat, String payload){
        int id = 0; //todo: needs db logic
        return id;
    }

    public void unregisterMessage(int id){
        //todo: needs db logic
    }

    public User getSender() { return sender; }
    public String getPayload() { return payload; }
    public int getId() { return id; }
    public Chat getChat() { return chat; }
    public long getCreationTime() { return creationTime; }

    @Override
    public String toString(){
        return sender + ": " + payload;
    }
}
