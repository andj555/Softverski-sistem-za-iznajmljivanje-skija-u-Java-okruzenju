/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.*;
import java.util.Objects;

/**
 *
 * @author Anđelija
 */
public class Osoba implements ApstraktniDomenskiObjekat {
    
    private int idOsoba;
    private String ime;
    private String prezime;
    private String telefon;
    private String jmbg;
    private Clanstvo clanstvo;

    public Osoba() {
    }

    public Osoba(int idOsoba, String ime, String prezime, String telefon, String jmbg, Clanstvo clanstvo) {
        this.idOsoba = idOsoba;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.jmbg = jmbg;
        this.clanstvo = clanstvo;
    }

    public Osoba(String ime, String prezime, String telefon, String jmbg, Clanstvo clanstvo) {
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.jmbg = jmbg;
        this.clanstvo = clanstvo;    }


    public int getIdOsoba() {
        return idOsoba;
    }

    public void setIdOsoba(int idOsoba) {
        this.idOsoba = idOsoba;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public Clanstvo getClanstvo() {
        return clanstvo;
    }

    public void setClanstvo(Clanstvo clanstvo) {
        this.clanstvo = clanstvo;
    }

    @Override
    public String toString() {
        return ime +" "+ prezime ;
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
        final Osoba other = (Osoba) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        return Objects.equals(this.jmbg, other.jmbg);
    }

    @Override
    public String vratiNazivTabele() {

        return "osoba"; 
                }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
List<ApstraktniDomenskiObjekat> lista = new ArrayList<ApstraktniDomenskiObjekat>();
    while (rs.next()) {
        int idOsoba = rs.getInt("osoba.idOsoba");
        String ime = rs.getString("osoba.ime");
        String prezime = rs.getString("osoba.prezime");
        String telefon = rs.getString("osoba.telefon");
        String jmbg = rs.getString("osoba.jmbg");
        
        int idClanstvo = rs.getInt("clanstvo.idClanstvo");
        String tipClanstva = rs.getString("clanstvo.tipClanstva");
        
        Clanstvo clanstvo = new Clanstvo();
        clanstvo.setIdClanstvo(idClanstvo);
        clanstvo.setTipClanstva(tipClanstva);
        
        Osoba o = new Osoba(idOsoba, ime, prezime, telefon, jmbg, clanstvo);
        lista.add(o);
        
    }
    return lista;
    }

    @Override
    public String vratiKolonuZaInsert() {
return "ime, prezime, telefon, jmbg, idClanstvo";
    }

    @Override
    public String vratiVrednostZaInsert() {
return "'"+ime+"', '"+prezime+"', '"+telefon+"', '"+jmbg+"', "+clanstvo.getIdClanstvo();
    }

    @Override
    public String vratiPK() {
return "osoba.idOsoba="+idOsoba;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "ime='" + ime + "', prezime='" + prezime + "', telefon='" + telefon + 
           "', jmbg='" + jmbg + "', idClanstvo=" + clanstvo.getIdClanstvo();
    }
    
    
    
    
    
}
