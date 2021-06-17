package de.htwsaar.vs.rmiMessenger;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Singleton-Design aktion, at least i try
 */
public class Database {
    public static Database instance(){
        if(uniqueInstance == null){
            uniqueInstance = new Database();
        }
        return uniqueInstance;
    }
    private Database(){
        // Todo: hier db connect implementierung einfügen
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:server.db");
            initializeDatabase(connection);
            System.out.println("DB Connection successfully.");
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    private void initializeDatabase(Connection connection){
         //Todo: Logik zum initialisieren der DB einfügen
    }
    private static Database uniqueInstance = null;
    private Connection connection = null;
}
