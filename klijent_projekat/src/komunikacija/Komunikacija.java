/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Clanstvo;
import domen.Iznajmljivanje;
import domen.Osoba;
import domen.RadnaSmena;
import domen.Skije;
import domen.StavkaIznajmljivanja;
import domen.Zaposleni;
import java.io.IOException;
import java.net.Socket;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Anđelija
 */
public class Komunikacija {
    
    private static Komunikacija instance;
    private Socket s;
    private Posiljalac posiljalac;
    private Primalac primalac;

    private Komunikacija() {
    }
    
    public static Komunikacija getInstance(){
    
    if(instance==null){
    
    instance = new Komunikacija();
   
    }
    return instance;
    }
    
    
    public void konekcija(){
    
        try {
            s = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(s);
            primalac = new Primalac(s);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    } 
    
    public Zaposleni login(String username, String password){
        
        Zaposleni zaposleni = new Zaposleni();
        zaposleni.setKorisnickoIme(username);
        zaposleni.setSifra(password);
        
    Klijentski_zahtev kz = new Klijentski_zahtev(Operacija.LOGIN, zaposleni);
    
    posiljalac.posalji(kz);
    Serverski_odgovor odgovor = (Serverski_odgovor) primalac.primi();
    
    zaposleni = (Zaposleni) odgovor.getOdgovor();
    
    return zaposleni;
    }

    public List<Osoba> ucitajOsobe() {
        
        Klijentski_zahtev kz = new Klijentski_zahtev(Operacija.UCITAJ_OSOBE, null);
        List<Osoba> osobe = new ArrayList<Osoba>();
        
    posiljalac.posalji(kz);
    Serverski_odgovor odgovor = (Serverski_odgovor) primalac.primi();
    
    osobe = (List<Osoba>) odgovor.getOdgovor();
    return osobe;
    }

    public void obrisiOsobu(Osoba osoba) throws Exception {
        Klijentski_zahtev kz = new Klijentski_zahtev(Operacija.OBRISI_OSOBU, osoba);
        posiljalac.posalji(kz);
        
        Serverski_odgovor odgovor = (Serverski_odgovor) primalac.primi();
        if (odgovor.getOdgovor()==null){
            //JOptionPane.showMessageDialog(null, "Sistem je obrisao osobu");
        }else{
       // JOptionPane.showMessageDialog(null, "Sistem ne moze da obrise osobu");
        throw new Exception("GRESKA, KOMUNIKACIJA");
        }
           
    }

    public void dodajOsobu(Osoba osoba) throws Exception {
        Klijentski_zahtev kz = new Klijentski_zahtev(Operacija.DODAJ_OSOBU, osoba);
        posiljalac.posalji(kz);
        
        Serverski_odgovor odgovor = (Serverski_odgovor) primalac.primi();
         if (odgovor.getOdgovor()==null){
           // JOptionPane.showMessageDialog(null, "Sistem je zapamtio osobu");
        }else{
     //   JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti osobu");
        throw new Exception("GRESKA, KOMUNIKACIJA");
        }        
    }

    public List<Clanstvo> ucitajClanstva() {
         Klijentski_zahtev kz = new Klijentski_zahtev(Operacija.UCITAJ_CLANSTVA, null);
        List<Clanstvo> clanstva = new ArrayList<Clanstvo>();
        
    posiljalac.posalji(kz);
    Serverski_odgovor odgovor = (Serverski_odgovor) primalac.primi();
    
    clanstva = (List<Clanstvo>) odgovor.getOdgovor();
    return clanstva;
    }

    public void promeniOsobu(Osoba osoba) throws Exception {
        Klijentski_zahtev kz = new Klijentski_zahtev(Operacija.PROMENI_OSOBU, osoba);
        posiljalac.posalji(kz);
        
        Serverski_odgovor odgovor = (Serverski_odgovor) primalac.primi();
        if (odgovor.getOdgovor()==null){
         //   JOptionPane.showMessageDialog(null, "Sistem je zapamtio osobu");
     
        }else{
      //  JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti osobu");
        throw new Exception("GRESKA, KOMUNIKACIJA");
         }
    }

    public List<Iznajmljivanje> ucitajIznajmljivanja() {
       Klijentski_zahtev kz = new Klijentski_zahtev(Operacija.UCITAJ_IZNAJMLJIVANJA, null);
        List<Iznajmljivanje> iznajmljivanja = new ArrayList<Iznajmljivanje>();
        
    posiljalac.posalji(kz);
    Serverski_odgovor odgovor = (Serverski_odgovor) primalac.primi();
    
    iznajmljivanja = (List<Iznajmljivanje>) odgovor.getOdgovor();
    return iznajmljivanja;
    }

    public List<Skije> ucitajSkije() {
         Klijentski_zahtev kz = new Klijentski_zahtev(Operacija.UCITAJ_SKIJE, null);
        List<Skije> skije = new ArrayList<Skije>();
        
    posiljalac.posalji(kz);
    Serverski_odgovor odgovor = (Serverski_odgovor) primalac.primi();
    
    skije = (List<Skije>) odgovor.getOdgovor();
    return skije;
    }

    public void ubaciIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
        Klijentski_zahtev kz = new Klijentski_zahtev(Operacija.KREIRAJ_IZNAJMLJIVANJE, iznajmljivanje);
        posiljalac.posalji(kz);
        
        Serverski_odgovor odgovor = (Serverski_odgovor) primalac.primi();
         if (odgovor.getOdgovor()==null){
       //     JOptionPane.showMessageDialog(null, "Sistem je kreirao iznamjljivanje");
        }else{
       // JOptionPane.showMessageDialog(null, "Sistem ne moze da kreira iznajmljivanje");
        throw new Exception("GRESKA, KOMUNIKACIJA");
        }        
    }

   

    public List<Zaposleni> ucitajZaposlene() {
  Klijentski_zahtev kz = new Klijentski_zahtev(Operacija.UCITAJ_ZAPOSLENE, null);
        List<Zaposleni> zaposleni = new ArrayList<Zaposleni>();
        
    posiljalac.posalji(kz);
    Serverski_odgovor odgovor = (Serverski_odgovor) primalac.primi();
    
   zaposleni = (List<Zaposleni>) odgovor.getOdgovor();
    return zaposleni;           

    }

    public void promeniIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
        
        Klijentski_zahtev kz = new Klijentski_zahtev(Operacija.PROMENI_IZNAJMLJIVANJE, iznajmljivanje);
        posiljalac.posalji(kz);
        
        Serverski_odgovor odgovor = (Serverski_odgovor) primalac.primi();
        if (odgovor.getOdgovor()==null){
           // JOptionPane.showMessageDialog(null, "Sistem je zapamtio iznajmljivanje");
        
        }else{
       // JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti iznajmljivanje");
        throw new Exception("GRESKA, KOMUNIKACIJA");
         }
    }

    public List<Iznajmljivanje> pretraziIznajmljivanja(Iznajmljivanje i) {
        
       Klijentski_zahtev kz = new Klijentski_zahtev(Operacija.PRETRAZI_IZNAJMLJIVANJA, i);
        List<Iznajmljivanje> iznajmljivanja = new ArrayList<Iznajmljivanje>();
        
    posiljalac.posalji(kz);
    Serverski_odgovor odgovor = (Serverski_odgovor) primalac.primi();
    
    iznajmljivanja = (List<Iznajmljivanje>) odgovor.getOdgovor();
    return iznajmljivanja;
    }

    public List<Osoba> pretraziOsobe(Osoba o) {
           
       Klijentski_zahtev kz = new Klijentski_zahtev(Operacija.PRETRAZI_OSOBE, o);
        List<Osoba> osobe = new ArrayList<Osoba>();
        
    posiljalac.posalji(kz);
    Serverski_odgovor odgovor = (Serverski_odgovor) primalac.primi();
    
    osobe = (List<Osoba>) odgovor.getOdgovor();
    return osobe;
        
    }

    public List<RadnaSmena> ucitajRadneSmene() {
        Klijentski_zahtev kz = new Klijentski_zahtev(Operacija.UCITAJ_RADNESMENE, null);
        List<RadnaSmena> smene = new ArrayList<RadnaSmena>();
        
    posiljalac.posalji(kz);
    Serverski_odgovor odgovor = (Serverski_odgovor) primalac.primi();
    
   smene = (List<RadnaSmena>) odgovor.getOdgovor();
    return smene;           

        
    }

    public void ubaciRadnuSmenu(RadnaSmena smena) throws Exception {
   Klijentski_zahtev kz = new Klijentski_zahtev(Operacija.UBACI_RADNUSMENU, smena);
        posiljalac.posalji(kz);
        
        Serverski_odgovor odgovor = (Serverski_odgovor) primalac.primi();
         if (odgovor.getOdgovor()!=null){
          throw new Exception("GRESKA, KOMUNIKACIJA");
         }  
    }
    
}
