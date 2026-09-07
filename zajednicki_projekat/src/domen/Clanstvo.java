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
public class Clanstvo implements ApstraktniDomenskiObjekat {
    private int idClanstvo;
    private String tipClanstva;

    public Clanstvo() {
    }

    public Clanstvo(int idClanstvo, String tipClanstva) {
        this.idClanstvo = idClanstvo;
        this.tipClanstva = tipClanstva;
    }

    public int getIdClanstvo() {
        return idClanstvo;
    }

    public void setIdClanstvo(int idClanstvo) {
        this.idClanstvo = idClanstvo;
    }

    public String getTipClanstva() {
        return tipClanstva;
    }

    public void setTipClanstva(String tipClanstva) {
        this.tipClanstva = tipClanstva;
    }

    @Override
    public String toString() {
        return tipClanstva ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Clanstvo other = (Clanstvo) obj;
        return Objects.equals(this.tipClanstva, other.tipClanstva);
    }

    @Override
    public String vratiNazivTabele() {
return "clanstvo";
        }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
List<ApstraktniDomenskiObjekat> lista = new ArrayList<ApstraktniDomenskiObjekat>();
    
    while (rs.next()) {
       
        int idClanstvo = rs.getInt("idClanstvo");
        String tipClanstva = rs.getString("tipClanstva");

        Clanstvo clanstvo = new Clanstvo(idClanstvo, tipClanstva);
  
        lista.add(clanstvo);
    }
    return lista;   
    
    }

    @Override
    public String vratiKolonuZaInsert() {
return "tipClanstva";
    }

    @Override
    public String vratiVrednostZaInsert() {
return "'"+tipClanstva+"'";    
    }

    @Override
    public String vratiPK() {
        return "clanstvo.idClanstvo="+idClanstvo;
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
