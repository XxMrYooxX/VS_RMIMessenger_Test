package de.htwsaar.vs.rmiMessenger;

public class User {
    private int id;
    private String username;
    private String password;
    private long registrationDate;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.registrationDate = (System.currentTimeMillis() / 1000);
        this.id = registerUser(username, password, registrationDate);
    }

    public User(int id){
        //todo: db logic
    }

    public int registerUser(String username, String password, long registrationDate){
        //todo: db logic und return der ID
    }
    public void unregisterUser(int id){
        //todo: db logic
    }
    public void changePassword(int id, String currentPassword, String newPassword){
        if(currentPassword == this.password){
            setPassword(newPassword);
        }
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    private void setPassword(String password) { this.password = password; }
}
