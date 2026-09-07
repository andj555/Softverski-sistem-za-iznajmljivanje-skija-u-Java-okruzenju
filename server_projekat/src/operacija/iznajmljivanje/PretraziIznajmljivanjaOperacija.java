/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.iznajmljivanje;

import domen.Iznajmljivanje;
import domen.Osoba;
import domen.StavkaIznajmljivanja;
import domen.Zaposleni;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Anđelija
 */
public class PretraziIznajmljivanjaOperacija extends ApstraktnaGenerickaOperacija {

        private List<Iznajmljivanje> lista = new ArrayList<>();
    
    @Override
    protected void preduslovi(Object param) throws Exception {
         if (param==null || !(param instanceof Iznajmljivanje)){
        throw new Exception("Sistem ne moze da nadje iznajmljivanje");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
        String upit = " JOIN osoba ON osoba.idOsoba = iznajmljivanje.idOsoba "
             + " JOIN zaposleni ON zaposleni.idZaposlen = iznajmljivanje.idZaposlen "
           + " JOIN clanstvo ON osoba.idClanstvo = clanstvo.idClanstvo";
        
        List<Iznajmljivanje>iznajmljivanja = dbbroker.getAll((Iznajmljivanje)param, upit);
        
        Iznajmljivanje i = (Iznajmljivanje) param;
        
     Integer id = i.getIdIznajmljivanje();
    Osoba osoba = i.getOsoba();
    Zaposleni zaposleni = i.getZaposleni();
    java.sql.Date datum = (java.sql.Date) i.getDatumRealizacije();
    
    
    for (Iznajmljivanje iz : iznajmljivanja) {
      boolean postoji = true;
    
      if (id != null && id>0) {
            if (!id.equals(iz.getIdIznajmljivanje())) {
                postoji = false;
            }
        }
      
      
      if (osoba != null) {
            if (iz.getOsoba() ==null || !osoba.equals(iz.getOsoba())) {
                postoji = false;
            }
        }
      
      if (zaposleni != null) {
            if (iz.getZaposleni() == null || !zaposleni.equals(iz.getZaposleni())) {
                postoji = false;
                
            }
        }
      
      if (datum != null) {
            if (iz.getDatumRealizacije() == null || !datum.equals(iz.getDatumRealizacije())){
                postoji = false;
            }
        }
    
         if (postoji) {
          String uslovZaStavke = " JOIN skije ON skije.idSkije = stavkaiznajmljivanja.idSkije WHERE idIznajmljivanje = " + iz.getIdIznajmljivanje();
          List<StavkaIznajmljivanja> stavke = dbbroker.getAll(new StavkaIznajmljivanja(), uslovZaStavke);
            
            if (stavke != null) {
                for (StavkaIznajmljivanja s : stavke) {
                   s.setIznajmljivanje(iz);
                }
                iz.setStavkeIznajmljivanja(stavke);
            }   
           lista.add(iz);
        }
      
    }
    
  }  
    
    public List<Iznajmljivanje> getLista(){
    return lista;
    }
    
}
