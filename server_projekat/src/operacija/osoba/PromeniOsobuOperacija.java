/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.osoba;

import domen.Osoba;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;
import domen.ApstraktniDomenskiObjekat;

/**
 *
 * @author Anđelija
 */
public class PromeniOsobuOperacija extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param==null || !(param instanceof Osoba)){
        throw new Exception("Sistem ne moze da zapamti osobu");
        }
        
        Osoba o = (Osoba) param;
        if (o.getIme()==null || o.getIme().isEmpty()){
        throw new Exception("Sistem ne moze da zapamti osobu");
        }
        if (o.getPrezime()==null || o.getPrezime().isEmpty()){
        throw new Exception("Sistem ne moze da zapamti osobu");
        }
        if (o.getTelefon()==null || o.getTelefon().isEmpty() || o.getTelefon().length()<6 || o.getTelefon().length()>15){
        throw new Exception("Sistem ne moze da zapamti osobu");
        }
        if (o.getJmbg()==null || o.getJmbg().isEmpty() || o.getJmbg().length() != 13){
        throw new Exception("Sistem ne moze da zapamti osobu");
        }
        String uslov =" JOIN clanstvo ON osoba.idClanstvo = clanstvo.idClanstvo WHERE osoba.jmbg = '" + o.getJmbg() + "' AND osoba.idOsoba != " + o.getIdOsoba();
        List<ApstraktniDomenskiObjekat> postoji = dbbroker.getAll(o, uslov);
        if (!postoji.isEmpty()) {
        throw new Exception("Sistem ne moze da zapamti osobu");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        dbbroker.edit(param);
    }
    
}
