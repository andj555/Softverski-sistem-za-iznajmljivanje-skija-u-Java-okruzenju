/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.radnaSmena;

import domen.RadnaSmena;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Anđelija
 */
public class UcitajRadnuSmenuOperacija extends ApstraktnaGenerickaOperacija {

    List<RadnaSmena> smene;
        
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        smene = dbbroker.getAll(param, null);
    }
    
    public List<RadnaSmena> getLista() {
        return smene;
    }
}
