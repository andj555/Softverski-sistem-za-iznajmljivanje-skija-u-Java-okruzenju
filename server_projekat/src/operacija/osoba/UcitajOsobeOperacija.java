/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.osoba;

import domen.Osoba;
import java.util.*;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Anđelija
 */
public class UcitajOsobeOperacija extends ApstraktnaGenerickaOperacija {
    
    List<Osoba> osobe;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
   String uslov = " JOIN clanstvo ON osoba.idClanstvo = clanstvo.idClanstvo;";
       osobe = dbbroker.getAll(param, uslov);
        System.out.println(uslov);
    }

    public List<Osoba> getOsobe() {
        return osobe;
    }
    
    
    
}
