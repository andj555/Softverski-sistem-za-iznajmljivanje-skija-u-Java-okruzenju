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
public class Skije implements ApstraktniDomenskiObjekat{
    
    private int idSkije;
    private String model;
    private double velicina;
    private double cena;
    private String tip;

    public Skije() {
    }

    public Skije(int idSkije, String model, double velicina, double cena, String tip) {
        this.idSkije = idSkije;
        this.model = model;
        this.velicina = velicina;
        this.cena = cena;
        this.tip = tip;
    }

    public int getIdSkije() {
        return idSkije;
    }

    public void setIdSkije(int idSkije) {
        this.idSkije = idSkije;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getVelicina() {
        return velicina;
    }

    public void setVelicina(double velicina) {
        this.velicina = velicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return model + " - " + velicina;
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
        final Skije other = (Skije) obj;
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        return Objects.equals(this.tip, other.tip);
    }

    @Override
    public String vratiNazivTabele() {
    return "skije";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
    List<ApstraktniDomenskiObjekat> lista = new ArrayList<ApstraktniDomenskiObjekat>();
    while (rs.next()) {
        int idSkije = rs.getInt("idSkije");
        String model = rs.getString("model");
        double velicina = rs.getDouble("velicina");
        double cena = rs.getDouble("cena");
        String tip = rs.getString("tip");

        Skije skije = new Skije(idSkije, model, velicina, cena, tip);
        lista.add(skije);
    }
    return lista;  
    
    }

    @Override
    public String vratiKolonuZaInsert() {
return "model,velicina,cena,tip";
    }

    @Override
    public String vratiVrednostZaInsert() {
return "'"+model+"', '"+velicina+"', '"+cena+"' ,' "+tip+"'";
    }

    @Override
    public String vratiPK() {
return "skije.idSkije="+idSkije;
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
