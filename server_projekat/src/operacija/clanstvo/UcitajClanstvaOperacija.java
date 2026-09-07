/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.clanstvo;

import domen.Clanstvo;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Anđelija
 */
public class UcitajClanstvaOperacija extends ApstraktnaGenerickaOperacija {
    
    List<Clanstvo> clanstva;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        clanstva = dbbroker.getAll(param, null);
    }
    
     public List<Clanstvo> getClanstva() {
        
        return clanstva;
    }
    
    
}
