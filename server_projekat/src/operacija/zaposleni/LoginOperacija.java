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
public class LoginOperacija extends ApstraktnaGenerickaOperacija {

    Zaposleni z;

    public Zaposleni getZaposleni() {
        return z;
    }

    public void setZaposleni(Zaposleni z) {
        this.z = z;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param==null || !(param instanceof Zaposleni)){
        throw new Exception("Korisnicko ime i sifra nisu ispravni");
        }
        Zaposleni z = (Zaposleni) param;
        if (z.getKorisnickoIme()==null || z.getKorisnickoIme().isEmpty() || z.getKorisnickoIme().length()<=4){
        throw new Exception("Korisnicko ime i sifra nisu ispravni");
        }
        if (z.getSifra()==null || z.getSifra().isEmpty() || z.getSifra().length()<=4){
        throw new Exception("Korisnicko ime i sifra nisu ispravni");
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Zaposleni> zaposleni = dbbroker.getAll((Zaposleni)param, null);
        System.out.println("zaposleni:"+zaposleni);
        
       
        for (Zaposleni zap: zaposleni){
        if(zap.equals((Zaposleni)param)){
        z = zap;
            System.out.println(z.getKorisnickoIme());
        return;
        }
        
        }
        z= null;
    }
    
}
