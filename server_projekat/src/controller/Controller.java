/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Clanstvo;
import domen.Iznajmljivanje;
import domen.Osoba;
import domen.RadnaSmena;
import domen.Skije;
import domen.StavkaIznajmljivanja;
import domen.Zaposleni;
import java.util.List;
import operacija.clanstvo.UcitajClanstvaOperacija;
import operacija.iznajmljivanje.UbaciIznajmljivanjeOperacija;
import operacija.iznajmljivanje.PretraziIznajmljivanjaOperacija;
import operacija.iznajmljivanje.PromeniIznajmljivanjeOperacija;
import operacija.iznajmljivanje.UcitajIznajmljivanjaOperacija;
import operacija.osoba.UcitajOsobeOperacija;
import operacija.zaposleni.LoginOperacija;
import operacija.osoba.UbaciOsobuOperacija;
import operacija.osoba.ObrisiOsobuOperacija;
import operacija.osoba.PretraziOsobeOperacija;
import operacija.osoba.PromeniOsobuOperacija;
import operacija.radnaSmena.UcitajRadnuSmenuOperacija;
import operacija.radnaSmena.UbaciRadnuSmenuOperacija;
import operacija.skije.UcitajSkijeOperacija;
import operacija.zaposleni.UcitajZaposleneOperacija;

/**
 *
 * @author Anđelija
 */
public class Controller {
    
    private static Controller instance;

    public Controller() {
    }
    
    public static Controller getInstance(){
    
    if (instance==null){
    instance = new Controller();
    
    }
    return instance;
    }

    public Zaposleni login(Zaposleni z) throws Exception {
        
          LoginOperacija login = new LoginOperacija();
     
            login.izvrsi(z, null);
            
        
        return login.getZaposleni();
    }

    public List<Osoba> ucitajOsobe() throws Exception {
        UcitajOsobeOperacija ucitajOsobe = new UcitajOsobeOperacija();
        
            ucitajOsobe.izvrsi(new Osoba(), null);
       
        System.out.println("Lista osoba: " + ucitajOsobe.getOsobe());
        return ucitajOsobe.getOsobe();
    }

    public void obrisiOsobu(Osoba osoba) throws Exception {
        ObrisiOsobuOperacija obrisiOsobu = new ObrisiOsobuOperacija();
       
            obrisiOsobu.izvrsi(osoba, null);
       
    }

    public void dodajOsobu(Osoba osoba) throws Exception {
        UbaciOsobuOperacija dodajOsobu = new UbaciOsobuOperacija();
        
            dodajOsobu.izvrsi(osoba, null);
            System.out.println("OVO SE ISPISUJE AKO JE SISTEM IZVRSIO KOD USPESNO");
        
    }

    public List<Clanstvo> ucitajClanstva() throws Exception {
        UcitajClanstvaOperacija ucitajClanstva = new UcitajClanstvaOperacija();
        
            ucitajClanstva.izvrsi(new Clanstvo(), null);
        
        System.out.println("Lista clanstva: " + ucitajClanstva.getClanstva());
        return ucitajClanstva.getClanstva();   
    }

    public void promeniOsobu(Osoba osoba) throws Exception {
        PromeniOsobuOperacija promeniOsobu = new PromeniOsobuOperacija();
       
            promeniOsobu.izvrsi(osoba, null);
        
    }

    public List<Iznajmljivanje> ucitajIznajmljivanja() throws Exception {
    UcitajIznajmljivanjaOperacija ucitajIznajmljivanja = new UcitajIznajmljivanjaOperacija();
      
            ucitajIznajmljivanja.izvrsi(new Iznajmljivanje(), null);
        
        System.out.println("Lista iznajmljivanja: " + ucitajIznajmljivanja.getIznajmljivanja());
        return ucitajIznajmljivanja.getIznajmljivanja();
    }


    public List<Skije> ucitajSkije() throws Exception {
        UcitajSkijeOperacija ucitajSkije = new UcitajSkijeOperacija();
     
            ucitajSkije.izvrsi(new Skije(), null);
        
        System.out.println("Lista skija: " + ucitajSkije.getSkije());
        return ucitajSkije.getSkije();   
        
    }

    public void ubaciIznajmljivanje(Iznajmljivanje i) throws Exception {
        UbaciIznajmljivanjeOperacija dodajIznajmljivanje = new UbaciIznajmljivanjeOperacija();
         
            dodajIznajmljivanje.izvrsi(i, null);
       
    }

    public List<Zaposleni> ucitajZaposlene() throws Exception {
          UcitajZaposleneOperacija ucitajZaposlene = new UcitajZaposleneOperacija();
      
            ucitajZaposlene.izvrsi(new Zaposleni(), null);
        
        System.out.println("Lista zaposlenih: " + ucitajZaposlene.getZaposlene());
        return ucitajZaposlene.getZaposlene();   
    }

    public void promeniIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
         PromeniIznajmljivanjeOperacija promeniIznajmljivanje = new PromeniIznajmljivanjeOperacija();
        
            promeniIznajmljivanje.izvrsi(iznajmljivanje, null);
       
    }

    public List<Iznajmljivanje> pretraziIznajmljivanja(Iznajmljivanje i) throws Exception {
        PretraziIznajmljivanjaOperacija pretraziIznajmljivanja = new PretraziIznajmljivanjaOperacija();
        
            pretraziIznajmljivanja.izvrsi(i, null);
       
        System.out.println("Lista izn.: " + pretraziIznajmljivanja.getLista());
        return pretraziIznajmljivanja.getLista();   
        
    }

    public List<Osoba> pretraziOsobe(Osoba osoba) throws Exception {
       PretraziOsobeOperacija pretraziOsobe = new PretraziOsobeOperacija();
    
            pretraziOsobe.izvrsi(osoba, null);
      
        System.out.println("Lista izn.: " + pretraziOsobe.getLista());
        return pretraziOsobe.getLista();   
        
    }

    public List<RadnaSmena> ucitajRadneSmene() throws Exception {
        UcitajRadnuSmenuOperacija ucitajRadnuSmenu = new UcitajRadnuSmenuOperacija();
     
         ucitajRadnuSmenu.izvrsi(new RadnaSmena(), null);
        
        System.out.println("Lista skija: " + ucitajRadnuSmenu.getLista());
        return ucitajRadnuSmenu.getLista();   
        
    }

    public void ubaciRadnuSmenu(RadnaSmena smena) throws Exception {
       UbaciRadnuSmenuOperacija ubaciRadnuSmenu = new UbaciRadnuSmenuOperacija();
        
          ubaciRadnuSmenu.izvrsi(smena, null);
          System.out.println("OVO SE ISPISUJE AKO JE SISTEM IZVRSIO KOD USPESNO"); 
       }

}
