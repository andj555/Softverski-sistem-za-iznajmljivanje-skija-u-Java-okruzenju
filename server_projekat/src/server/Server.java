/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author Anđelija
 */
public class Server extends Thread{
    
     ServerSocket serverskiSoket;
     boolean b=false;
     List<ObradaKlijentskihZahteva> lista;

    public Server() {
     
     lista = new ArrayList<>();
        
    }

    @Override
    public void run() {
      try {
            serverskiSoket = new ServerSocket(9000);
            
            while (!b){
                
                Socket s = serverskiSoket.accept();
                System.out.println("Klijent se povezao!");
                
                
                ObradaKlijentskihZahteva nit = new ObradaKlijentskihZahteva(s);
                lista.add(nit);
                nit.start();
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
    
    public void zaustavi(){
    
        b=true;
         try {
            for (ObradaKlijentskihZahteva kz: lista){
            kz.zaustavi();
            }
         
             serverskiSoket.close();
         } catch (IOException ex) {
             Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
         }
    
    }
    
    
}
