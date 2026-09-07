/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domen.Zaposleni;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Klijentski_zahtev;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Serverski_odgovor;
import controller.Controller;
import domen.Clanstvo;
import domen.Iznajmljivanje;
import domen.Osoba;
import domen.RadnaSmena;
import domen.Skije;
import domen.StavkaIznajmljivanja;
import java.util.*;

/**
 *
 * @author Anđelija
 */
public class ObradaKlijentskihZahteva extends Thread{
    
    Socket s;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean b=false; 
 
    
    public ObradaKlijentskihZahteva(Socket s) {
        this.s=s;
        
        posiljalac = new Posiljalac(s);
        primalac = new Primalac(s);
    }
    
    
    
    public void run(){
    
        while (!b){
            
          
            try {
                Klijentski_zahtev kz = (Klijentski_zahtev) primalac.primi();
                Serverski_odgovor so = new Serverski_odgovor();
                
                switch (kz.getOperacija()) {
                    case LOGIN:
                        try{
                        Zaposleni zaposleni = (Zaposleni) kz.getParametar();
                        zaposleni = Controller.getInstance().login(zaposleni);
                        so.setOdgovor(zaposleni);}
                        catch(Exception ex){
                        so.setOdgovor(null);
                        System.out.println("greska u obradi klijentskih zahteva");
                        }
                        break;
                    case UCITAJ_OSOBE:
                        List<Osoba> osobe = Controller.getInstance().ucitajOsobe();
                        
                        so.setOdgovor(osobe);
                        break;
                    case OBRISI_OSOBU:
                        try{
                            Osoba osoba = (Osoba) kz.getParametar();
                            Controller.getInstance().obrisiOsobu(osoba);
                            so.setOdgovor(null);
                        }catch (Exception ex){
                            so.setOdgovor(ex);
                            ex.printStackTrace();
                        }
                        break;
                    case DODAJ_OSOBU:
                        try{
                            Osoba osoba = (Osoba) kz.getParametar();
                            Controller.getInstance().dodajOsobu(osoba);
                            so.setOdgovor(null);
                        }catch (Exception ex){
                            so.setOdgovor(ex);
                            ex.printStackTrace();
                        }
                        break;
                    case UCITAJ_CLANSTVA:
                        List<Clanstvo> clanstva = Controller.getInstance().ucitajClanstva();
                        so.setOdgovor(clanstva);
                        break;
                    case UCITAJ_ZAPOSLENE:
                        List<Zaposleni> svizaposleni = Controller.getInstance().ucitajZaposlene();
                        System.out.println("Iz obrade kl. zahteva: " +svizaposleni);
                        so.setOdgovor(svizaposleni);
                        break;
                    case PROMENI_OSOBU:
                        try{
                            Osoba osoba = (Osoba) kz.getParametar();
                            Controller.getInstance().promeniOsobu(osoba);
                            so.setOdgovor(null);
                        }catch (Exception e){
                            e.printStackTrace();
                            so.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_IZNAJMLJIVANJA:
                        List<Iznajmljivanje> iznajmljivanja = Controller.getInstance().ucitajIznajmljivanja();
                        System.out.println("Iz obrade kl. zahteva: "+ iznajmljivanja);
                        so.setOdgovor(iznajmljivanja);
                        break;
                    
                    case UCITAJ_SKIJE:
                        List<Skije> skije = Controller.getInstance().ucitajSkije();
                        System.out.println("Iz obrade kl. zahteva: " + skije);
                        so.setOdgovor(skije);
                        break;
                    case KREIRAJ_IZNAJMLJIVANJE:
                        try{
                            Iznajmljivanje i = (Iznajmljivanje) kz.getParametar();
                            Controller.getInstance().ubaciIznajmljivanje(i);
                            so.setOdgovor(null);
                        }
                        catch (Exception ex){
                            so.setOdgovor(ex);
                            ex.printStackTrace();
                        }
                        break;
                  
                    case PROMENI_IZNAJMLJIVANJE:
                        try{
                            Iznajmljivanje iznajmljivanje = (Iznajmljivanje) kz.getParametar();
                            Controller.getInstance().promeniIznajmljivanje(iznajmljivanje);
                            so.setOdgovor(null);
                         
                        }catch (Exception ex){
                            so.setOdgovor(ex);
                            ex.printStackTrace();
                        }
                        break;
                    case PRETRAZI_IZNAJMLJIVANJA:
                        try{
                            Iznajmljivanje iz = (Iznajmljivanje) kz.getParametar();
                            List<Iznajmljivanje> lista = Controller.getInstance().pretraziIznajmljivanja(iz);
                            so.setOdgovor(lista);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case PRETRAZI_OSOBE:
                        try{
                            Osoba osoba = (Osoba) kz.getParametar();
                            List<Osoba> o = Controller.getInstance().pretraziOsobe(osoba);
                            so.setOdgovor(o);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case UCITAJ_RADNESMENE:
                        List<RadnaSmena> smene = Controller.getInstance().ucitajRadneSmene();
                        System.out.println("Iz obrade kl. zahteva: " + smene);
                        so.setOdgovor(smene);
                        break;
                    case UBACI_RADNUSMENU:
                        try{
                            RadnaSmena smena = (RadnaSmena) kz.getParametar();
                            Controller.getInstance().ubaciRadnuSmenu(smena);
                            so.setOdgovor(null);
                        }catch (Exception ex){
                            so.setOdgovor(ex);
                            ex.printStackTrace();
                        }
                        break;
                        
                    default:
                        System.out.println("GRESKA, NEPOSTOJECA OPERACIJA");
                }
                
                posiljalac.posalji(so);
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
            
         }
        
        
    }
            
    public void zaustavi(){
    
        b=true;
        try {
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    interrupt();
    }
    
}
