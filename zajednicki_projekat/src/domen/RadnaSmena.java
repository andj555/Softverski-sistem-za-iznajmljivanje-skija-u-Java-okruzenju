/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.*;
import java.util.Objects;

/**
 *
 * @author Anđelija
 */
public class RadnaSmena implements ApstraktniDomenskiObjekat{
    
    private int idRadnaSmena;
    private String tipSmene;
    private LocalTime vremePocetka;
    private LocalTime vremeKraja;

    public RadnaSmena() {
    }

    public RadnaSmena(int idRadnaSmena, String tipSmene, LocalTime vremePocetka, LocalTime vremeKraja) {
        this.idRadnaSmena = idRadnaSmena;
        this.tipSmene = tipSmene;
        this.vremePocetka = vremePocetka;
        this.vremeKraja = vremeKraja;
    }

    public int getIdRadnaSmena() {
        return idRadnaSmena;
    }

    public void setIdRadnaSmena(int idRadnaSmena) {
        this.idRadnaSmena = idRadnaSmena;
    }

    public String getTipSmene() {
        return tipSmene;
    }

    public void setTipSmene(String tipSmene) {
        this.tipSmene = tipSmene;
    }

    public LocalTime getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(LocalTime vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public LocalTime getVremeKraja() {
        return vremeKraja;
    }

    public void setVremeKraja(LocalTime vremeKraja) {
        this.vremeKraja = vremeKraja;
    }

    @Override
    public String toString() {
        return tipSmene ;
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
        final RadnaSmena other = (RadnaSmena) obj;
        return Objects.equals(this.tipSmene, other.tipSmene);
    }

    @Override
    public String vratiNazivTabele() {
        return "radnasmena";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
List<ApstraktniDomenskiObjekat> lista = new ArrayList<ApstraktniDomenskiObjekat>();
    while (rs.next()) {
        int idRadnaSmena = rs.getInt("idRadnaSmena");
        String tipSmene = rs.getString("tipSmene");
        LocalTime vremePocetka = rs.getTime("vremePocetka").toLocalTime();
        LocalTime vremeKraja = rs.getTime("vremeKraja").toLocalTime();

        RadnaSmena radnaSmena = new RadnaSmena(idRadnaSmena, tipSmene, vremePocetka, vremeKraja);
        lista.add(radnaSmena);
    }
      return lista;

    }

    @Override
    public String vratiKolonuZaInsert() {
    return "tipSmene, vremePocetka, vremeKraja";
        }

    @Override
    public String vratiVrednostZaInsert() {
    return "'" + tipSmene +"', '" +vremePocetka + "', '" +vremeKraja + "'";
    }

    @Override
    public String vratiPK() {
    return "radnaSmena.idRadnaSmena="+idRadnaSmena;
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
