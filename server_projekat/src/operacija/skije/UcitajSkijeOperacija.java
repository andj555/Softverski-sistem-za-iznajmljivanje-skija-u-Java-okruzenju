/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.skije;

import domen.Skije;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Anđelija
 */
public class UcitajSkijeOperacija extends ApstraktnaGenerickaOperacija {

    List<Skije> skije;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
       skije =  dbbroker.getAll(param,null);
    }

    public List<Skije> getSkije() {
        return skije;
    }
    
    
    
}
