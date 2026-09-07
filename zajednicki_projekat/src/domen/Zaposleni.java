/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.*;
import java.util.Objects;

/**
 *
 * @author Anđelija
 */
public class Zaposleni implements ApstraktniDomenskiObjekat{
    
    private int idZaposlen;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String sifra;

    public Zaposleni() {
    }

    public Zaposleni(int idZaposlen, String ime, String prezime, String korisnickoIme, String sifra) {
        this.idZaposlen = idZaposlen;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public int getIdZaposlen() {
        return idZaposlen;
    }

    public void setIdZaposlen(int idZaposlen) {
        this.idZaposlen = idZaposlen;
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return ime+" "+prezime ;
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
        final Zaposleni other = (Zaposleni) obj;
       
        if (!Objects.equals(this.sifra, other.sifra)) {
            return false;
        }
        return Objects.equals(this.korisnickoIme, other.korisnickoIme);
    }

    @Override
    public String vratiNazivTabele() {
    return "zaposleni";
        }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
 
        
        List<ApstraktniDomenskiObjekat> lista= new ArrayList<ApstraktniDomenskiObjekat>();
        while (rs.next()){
        int idZaposlen = rs.getInt("idZaposlen");
        String ime= rs.getString("ime");
        String prezime= rs.getString("prezime");
        String korisnickoIme= rs.getString("korisnickoIme");
        String sifra= rs.getString("sifra");
           
        Zaposleni zaposleni = new Zaposleni(idZaposlen, ime, prezime, korisnickoIme, sifra);
        lista.add(zaposleni);
        }
        return lista;
       }

    @Override
    public String vratiKolonuZaInsert() {
return "ime,prezime,korisnickoIme,sifra";
    }

    @Override
    public String vratiVrednostZaInsert() {
return "'"+ime+"', '"+prezime+"', '"+korisnickoIme+"' , '"+sifra+"'"; 
    }

    @Override
    public String vratiPK() {
        return "zaposleni.idZaposleni="+idZaposlen;
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
