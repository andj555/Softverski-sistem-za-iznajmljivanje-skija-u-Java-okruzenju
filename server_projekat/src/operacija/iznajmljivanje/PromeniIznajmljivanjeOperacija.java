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
public class PromeniIznajmljivanjeOperacija extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        
        if (param==null || !(param instanceof Iznajmljivanje)){
        throw new Exception("Sistem ne moze da zapamti iznajmljivanje");
        }
        Iznajmljivanje i = (Iznajmljivanje) param;
        if (i.getZaposleni()==null){
        throw new Exception("Sistem ne moze da zapamti iznajmljivanje");
        }
        if (i.getOsoba()==null){
        throw new Exception("Sistem ne moze da zapamti iznajmljivanje");
        }
        if (i.getDatumRealizacije()==null){
        throw new Exception("Sistem ne moze da zapamti iznajmljivanje");
        }
        if (i.getStavkeIznajmljivanja().isEmpty()){
        throw new Exception("Sistem ne moze da zapamti iznajmljivanje");
        }
        if (i.getUkupanIznos()<=0){
        throw new Exception("Sistem ne moze da zapamti iznajmljivanje");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) param;
        dbbroker.edit(iznajmljivanje);
        String uslov = " JOIN skije on skije.idSkije=stavkaIznajmljivanja.idSkije WHERE idIznajmljivanje= " + iznajmljivanje.getIdIznajmljivanje();
        List<StavkaIznajmljivanja> stavke1 = dbbroker.getAll(new StavkaIznajmljivanja(), uslov);

        // doda se neka nova stavka
        // promeni se neka postojeca
        // obrise se neka postojeca
        
        // stareStavke - stavke1 - ucitane iz baze trenutne
        // noveStavke - stavke2 - stigle sa klijenta
        
        // statusStavke kod stavke 
       
        
        List<StavkaIznajmljivanja> stavke2 = iznajmljivanje.getStavkeIznajmljivanja(); 
        for (StavkaIznajmljivanja staraStavka: stavke1){
        
        staraStavka.setIznajmljivanje(iznajmljivanje);
        boolean postoji = false;
        
        for (StavkaIznajmljivanja novaStavka: stavke2){
            if (staraStavka.getRb()==novaStavka.getRb()){
                postoji=true;
                break;
            }
        }
           
        if (!postoji){
        dbbroker.delete(staraStavka);
        }
        
      }
        for (StavkaIznajmljivanja novaStavka: stavke2){
            novaStavka.setIznajmljivanje(iznajmljivanje);
            
            boolean postojiUBazi = false;
            boolean izmenjeno = false;
         for (StavkaIznajmljivanja staraStavka: stavke1){
           
             if (staraStavka.getRb()==novaStavka.getRb()){
                 if (!novaStavka.equals(staraStavka)){
                     izmenjeno =true;
                 }
                 postojiUBazi=true; break;
             }
            }
         if (izmenjeno){
         dbbroker.edit(novaStavka);
         }
         if (!postojiUBazi){
           dbbroker.add(novaStavka);
        }
         
        }
        
    }
    
}
