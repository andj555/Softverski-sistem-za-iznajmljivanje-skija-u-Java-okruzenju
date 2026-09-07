/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.osoba;

import domen.Clanstvo;
import domen.Iznajmljivanje;
import domen.Osoba;
import domen.Zaposleni;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Anđelija
 */
public class PretraziOsobeOperacija extends ApstraktnaGenerickaOperacija {

    List<Osoba> lista = new ArrayList<>();
    
    @Override
    protected void preduslovi(Object param) throws Exception {
       
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
        String uslov = " JOIN clanstvo  ON clanstvo.idClanstvo = osoba.idClanstvo";
           System.out.println("USLOV ZA PREGTRAGU :  "+uslov);
           System.out.println("STA JE STIGLO : " + param);
        List<Osoba>osobe = dbbroker.getAll((Osoba)param, uslov);
        System.out.println("LISTA OSOBA: "+osobe);
        Osoba o = (Osoba) param;
        
        String ime = o.getIme().trim();
        String prezime = o.getPrezime().trim();
        String telefon = o.getTelefon().trim();
        String jmbg = o.getJmbg().trim();
        Clanstvo clanstvo = o.getClanstvo();
        
        for (Osoba os : osobe) {
      boolean postoji = true;
       
        
        if (ime != null && !ime.isEmpty()) {
                if (!os.getIme().toLowerCase().startsWith(ime.toLowerCase())) {
                    postoji = false;
                }
            }
        
        if (prezime != null && !prezime.isEmpty()) {
                if (!os.getPrezime().toLowerCase().startsWith(prezime.toLowerCase())) {
                    postoji = false;
                }
            }
    
         if (telefon != null && !telefon.isEmpty()) {
                if (!os.getTelefon().toLowerCase().startsWith(telefon.toLowerCase())) {
                    postoji = false;
                }
            }
    
         if (jmbg != null && !jmbg.isEmpty()) {
                if (!os.getJmbg().toLowerCase().startsWith(jmbg.toLowerCase())) {
                    postoji = false;
                }
            }
    
         if (clanstvo != null ) {
                if (os.getClanstvo().getIdClanstvo() != clanstvo.getIdClanstvo()) {
                    postoji= false;
                }
            }
         
         if (postoji){
         lista.add(os);
         }
         
        }
       
    }
    
      public List<Osoba> getLista(){
        return lista;
        }
}
    
