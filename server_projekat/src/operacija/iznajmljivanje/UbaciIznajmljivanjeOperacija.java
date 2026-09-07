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
public class UbaciIznajmljivanjeOperacija extends ApstraktnaGenerickaOperacija {

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
        
        
        Iznajmljivanje i = (Iznajmljivanje) param ;
        int id = dbbroker.addReturnKey(i);
        i.setIdIznajmljivanje(id);
        
        List<StavkaIznajmljivanja> listaStavki = i.getStavkeIznajmljivanja();
        for (StavkaIznajmljivanja stavka : listaStavki) {
        
        stavka.setIznajmljivanje(i);
        dbbroker.add(stavka);
        }
    
    }
    
}
