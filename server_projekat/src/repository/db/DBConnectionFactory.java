/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import konfiguracija.Konfiguracija;

/**
 *
 * @author Anđelija
 */
public class DBConnectionFactory {
    
    private static DBConnectionFactory instance;
    private Connection connection;

    public DBConnectionFactory() {
        try {
            if (connection==null || connection.isClosed()) {
                
                String url = Konfiguracija.getInstance().getProperty("url");
                String username = Konfiguracija.getInstance().getProperty("username");
                String password = Konfiguracija.getInstance().getProperty("password");
                connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
       
        }
    }

    
    public static DBConnectionFactory getInstance() {
        
        if (instance == null) {
        instance = new DBConnectionFactory();
        
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    

    
}
