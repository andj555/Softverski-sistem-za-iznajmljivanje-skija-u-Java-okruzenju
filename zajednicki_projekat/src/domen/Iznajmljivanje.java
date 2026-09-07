/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.Date;
import java.util.*;

/**
 *
 * @author Anđelija
 */
public class Iznajmljivanje implements ApstraktniDomenskiObjekat{
    
    private int idIznajmljivanje;
    private double ukupanIznos;
    private double popust;
    private Date datumRealizacije;
    private Osoba osoba;
    private Zaposleni zaposleni;
    private List<StavkaIznajmljivanja> stavkeIznajmljivanja;

    public Iznajmljivanje() {
    }

    
    public Iznajmljivanje(int idIznajmljivanje, double ukupanIznos, double popust, Date datumRealizacije,
            Osoba osoba, Zaposleni zaposleni) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.ukupanIznos = ukupanIznos;
        this.popust = popust;
        this.datumRealizacije = datumRealizacije;
        this.osoba = osoba;
        this.zaposleni = zaposleni;
    }
    
    public Iznajmljivanje(int idIznajmljivanje, double ukupanIznos, double popust, Date datumRealizacije,
            Osoba osoba, Zaposleni zaposleni, List<StavkaIznajmljivanja> stavkeIznajmljivanja) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.ukupanIznos = ukupanIznos;
        this.popust = popust;
        this.datumRealizacije = datumRealizacije;
        this.osoba = osoba;
        this.zaposleni = zaposleni;
        this.stavkeIznajmljivanja = stavkeIznajmljivanja;
    }

    public int getIdIznajmljivanje() {
        return idIznajmljivanje;
    }

    public void setIdIznajmljivanje(int idIznajmljivanje) {
        this.idIznajmljivanje = idIznajmljivanje;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public Date getDatumRealizacije() {
        return datumRealizacije;
    }

    public void setDatumRealizacije(Date datumRealizacije) {
        this.datumRealizacije = datumRealizacije;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public List<StavkaIznajmljivanja> getStavkeIznajmljivanja() {
        return stavkeIznajmljivanja;
    }

    public void setStavkeIznajmljivanja(List<StavkaIznajmljivanja> stavkeIznajmljivanja) {
        this.stavkeIznajmljivanja = stavkeIznajmljivanja;
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
        final Iznajmljivanje other = (Iznajmljivanje) obj;
        if (this.idIznajmljivanje != other.idIznajmljivanje) {
            return false;
        }
        if (!Objects.equals(this.datumRealizacije, other.datumRealizacije)) {
            return false;
        }
        return Objects.equals(this.osoba, other.osoba);
    }

    @Override
    public String vratiNazivTabele() {
        return "iznajmljivanje";
        }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
         List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
    
    while (rs.next()) {

        int idIznajmljivanje = rs.getInt("idIznajmljivanje");
        double ukupanIznos = rs.getDouble("ukupanIznos");
        double popust = rs.getDouble("popust");
        Date datumRealizacije = rs.getDate("datumRealizacije");

        int idZaposlen = rs.getInt("zaposleni.idZaposlen");
        String imeZaposlen = rs.getString("zaposleni.ime");
        String prezimeZaposlen = rs.getString("zaposleni.prezime");
        String korisnickoIme = rs.getString("zaposleni.korisnickoIme");
        String sifra = rs.getString("zaposleni.sifra");
        
        Zaposleni zaposleni = new Zaposleni(idZaposlen, imeZaposlen, prezimeZaposlen, korisnickoIme, sifra);

        int idClanstvo = rs.getInt("idClanstvo");
        String tipClanstva = rs.getString("tipClanstva");
        Clanstvo clanstvo = new Clanstvo(idClanstvo, tipClanstva);

       int idOsoba = rs.getInt("osoba.idOsoba");
        String imeOsoba = rs.getString("osoba.ime");
        String prezimeOsoba = rs.getString("osoba.prezime");
        String telefon = rs.getString("osoba.telefon");
        String jmbg = rs.getString("osoba.jmbg");
        Osoba osoba = new Osoba(idOsoba, imeOsoba, prezimeOsoba, telefon, jmbg, clanstvo);

        List<StavkaIznajmljivanja> stavkeIznajmljivanja = new ArrayList<>(); 
        
        Iznajmljivanje iznajmljivanje = new Iznajmljivanje(idIznajmljivanje,  ukupanIznos,  popust, 
            datumRealizacije,  osoba,zaposleni, stavkeIznajmljivanja );

        lista.add(iznajmljivanje);
    }
    
    return lista;
    }

    @Override
    public String vratiKolonuZaInsert() {
return "ukupanIznos,popust,datumRealizacije,idZaposlen,idOsoba";
    }

    @Override
    public String vratiVrednostZaInsert() {
        java.sql.Date datumReal = new java.sql.Date(datumRealizacije.getTime());
return "'" + ukupanIznos + "', '" + popust + "', '" + datumReal + "', " 
            + zaposleni.getIdZaposlen() + ", " + osoba.getIdOsoba();
    }

    @Override
    public String vratiPK() {
return "iznajmljivanje.idIznajmljivanje="+idIznajmljivanje;
       }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        java.sql.Date datum = new java.sql.Date(datumRealizacije.getTime());
        
    return "ukupanIznos=" + ukupanIznos + ", popust=" + popust+ ", datumRealizacije='" + datum + 
           "', idOsoba=" + osoba.getIdOsoba()+ ", idZaposlen=" +zaposleni.getIdZaposlen();
    
    }
  
    @Override
    public String toString() {
        return "Iznajmljivanje{" + "idIznajmljivanje=" + idIznajmljivanje + ", ukupanIznos=" + ukupanIznos + ", popust=" + popust + ", datumRealizacije=" + datumRealizacije + ", osoba=" + osoba + ", zaposleni=" + zaposleni + ", stavkeIznajmljivanja=" + stavkeIznajmljivanja + '}';
    }
     
    
}
