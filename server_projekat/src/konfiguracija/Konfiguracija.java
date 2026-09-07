/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anđelija
 */
public class Konfiguracija {
    
    private static Konfiguracija instance;
    private Properties config;

    public Konfiguracija() {
        try {
            config = new Properties();
            config.load(new FileInputStream("C:\\Users\\Anđelija\\Documents\\NetBeansProjects\\server_projekat\\config\\config.properties"));
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    public static Konfiguracija getInstance(){
    if (instance== null){
    instance = new Konfiguracija();
    
    }
    return instance;
    }
    
    public String getProperty(String key){
    
    return config.getProperty(key, "n/a");
    
    }
    
    public void setProperty(String key, String value){
    
    config.setProperty(key, value);
    
    }
    
    public void izmeniProperties(){
    
        try {
            config.store(new FileOutputStream("C:\\Users\\Anđelija\\Documents\\NetBeansProjects\\server_projekat\\config\\config.properties"), null);
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    
    }
    
}
