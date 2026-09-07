/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.zaposleni;

import domen.Zaposleni;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Anđelija
 */
public class UcitajZaposleneOperacija extends ApstraktnaGenerickaOperacija {

    List <Zaposleni> zaposleni; 
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        zaposleni = dbbroker.getAll(param, null);
    }

    public List<Zaposleni> getZaposlene() {
        return zaposleni;
    }
    
    
}
