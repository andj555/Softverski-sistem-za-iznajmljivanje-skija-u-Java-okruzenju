/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.iznajmljivanje;

import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Anđelija
 */
public class UcitajIznajmljivanjaOperacija extends ApstraktnaGenerickaOperacija{

    List<Iznajmljivanje> iznajmljivanja;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Iznajmljivanje)) {
            throw new Exception("Sistem ne moze da ucita iznajmljivanje");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
       String uslov = " JOIN zaposleni ON iznajmljivanje.idZaposlen = zaposleni.idZaposlen \n" +
    "JOIN osoba ON iznajmljivanje.idOsoba = osoba.idOsoba \n" +
    "JOIN clanstvo ON osoba.idClanstvo = clanstvo.idClanstvo";
       
        iznajmljivanja = dbbroker.getAll(param, uslov);
        for (Iznajmljivanje iz : iznajmljivanja) {
        String uslovZaStavke = " JOIN skije ON skije.idSkije = stavkaiznajmljivanja.idSkije WHERE idIznajmljivanje = " + iz.getIdIznajmljivanje();
         System.out.println(uslovZaStavke);
        
         List<StavkaIznajmljivanja> stavke = dbbroker.getAll(new StavkaIznajmljivanja(), uslovZaStavke);
        System.out.println("KLASA UCITAJ IZNAJMLJIVANJA ");
        
        for (StavkaIznajmljivanja s : stavke) {
               s.setIznajmljivanje(iz);
            }
            
        iz.setStavkeIznajmljivanja(stavke);
        }
         
    }
    public List<Iznajmljivanje> getIznajmljivanja() {
        return iznajmljivanja;
    }
    
}
