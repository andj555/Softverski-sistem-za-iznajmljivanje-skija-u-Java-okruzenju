/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.Date;
import java.util.*;
import java.util.Objects;

/**
 *
 * @author Anđelija
 */
public class ZaposleniRadnaSmena implements ApstraktniDomenskiObjekat{
    
    private Date datumSmene;
    private Zaposleni zaposleni;
    private RadnaSmena radnaSmena;

    public ZaposleniRadnaSmena() {
    }

    public ZaposleniRadnaSmena(Date datumSmene, Zaposleni zaposleni, RadnaSmena radnaSmena) {
        this.datumSmene = datumSmene;
        this.zaposleni = zaposleni;
        this.radnaSmena = radnaSmena;
    }

    public Date getDatumSmene() {
        return datumSmene;
    }

    public void setDatumSmene(Date datumSmene) {
        this.datumSmene = datumSmene;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public RadnaSmena getRadnaSmena() {
        return radnaSmena;
    }

    public void setRadnaSmena(RadnaSmena radnaSmena) {
        this.radnaSmena = radnaSmena;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ZaposleniRadnaSmena other = (ZaposleniRadnaSmena) obj;
        if (!Objects.equals(this.datumSmene, other.datumSmene)) {
            return false;
        }
        if (!Objects.equals(this.zaposleni, other.zaposleni)) {
            return false;
        }
        return Objects.equals(this.radnaSmena, other.radnaSmena);
    }

    @Override
    public String toString() {
        return "ZaposleniRadnaSmena{" + "datumSmene=" + datumSmene + ", zaposleni=" + zaposleni + ", radnaSmena=" + radnaSmena + '}';
    }

    @Override
    public String vratiNazivTabele() {
    return "zaposleniradnasmena"; 
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKolonuZaInsert() {
return "datumSmene,zaposleni,radnaSmena";  
    }

    @Override
    public String vratiVrednostZaInsert() {
return "'"+datumSmene+"',"+zaposleni.getIdZaposlen()+","+radnaSmena.getIdRadnaSmena();  
    }

    @Override
    public String vratiPK() {
        return "zaposleni.idZaposleni="+zaposleni.getIdZaposlen()+"AND"+
                "radnaSmena.idRadnaSmena="+radnaSmena.getIdRadnaSmena();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
