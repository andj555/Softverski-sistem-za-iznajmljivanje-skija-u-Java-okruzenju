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
public class StavkaIznajmljivanja implements ApstraktniDomenskiObjekat{
    
    private int rb;
    private double iznos;
    private double cenaPoDanu;
    private Date datumPreuzimanja;
    private Date datumPovratka;
    private Skije skije;
    private Iznajmljivanje iznajmljivanje;

    public StavkaIznajmljivanja() {
    }

    public StavkaIznajmljivanja(int rb, double cenaPoDanu, Date datumPreuzimanja, Date datumPovratka,
            double iznos, Skije skije, Iznajmljivanje iznajmljivanje) {
        this.rb = rb;
        this.iznos = iznos;
        this.cenaPoDanu = cenaPoDanu;
        this.datumPreuzimanja = datumPreuzimanja;
        this.datumPovratka = datumPovratka;
        this.skije = skije;
        this.iznajmljivanje=iznajmljivanje;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public double getCenaPoDanu() {
        return cenaPoDanu;
    }

    public void setCenaPoDanu(double cenaPoDanu) {
        this.cenaPoDanu = cenaPoDanu;
    }

    public Date getDatumPreuzimanja() {
        return datumPreuzimanja;
    }

    public void setDatumPreuzimanja(Date datumPreuzimanja) {
        this.datumPreuzimanja = datumPreuzimanja;
    }

    public Date getDatumPovratka() {
        return datumPovratka;
    }

    public void setDatumPovratka(Date datumPovratka) {
        this.datumPovratka = datumPovratka;
    }

    public Skije getSkije() {
        return skije;
    }

    public void setSkije(Skije skije) {
        this.skije = skije;
    }

    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }

    public void setIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        this.iznajmljivanje = iznajmljivanje;
    }


    @Override
    public String toString() {
        return "StavkaIznajmljivanja{" + "skije=" + skije + '}';
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
        final StavkaIznajmljivanja other = (StavkaIznajmljivanja) obj;
        if (this.rb != other.rb) {
            return false;
        }
        if (Double.doubleToLongBits(this.iznos) != Double.doubleToLongBits(other.iznos)) {
            return false;
        }
        if (Double.doubleToLongBits(this.cenaPoDanu) != Double.doubleToLongBits(other.cenaPoDanu)) {
            return false;
        }
        if (!Objects.equals(this.datumPreuzimanja, other.datumPreuzimanja)) {
            return false;
        }
        if (!Objects.equals(this.datumPovratka, other.datumPovratka)) {
            return false;
        }
        if (!Objects.equals(this.skije, other.skije)) {
            return false;
        }
        return Objects.equals(this.iznajmljivanje, other.iznajmljivanje);
    }

    

    @Override
    public String vratiNazivTabele() {
    return "stavkaiznajmljivanja"; 
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        
        List<ApstraktniDomenskiObjekat> lista= new ArrayList<ApstraktniDomenskiObjekat>();
        while (rs.next()){
        
     int rb = rs.getInt("stavkaiznajmljivanja.rb");
        double iznos = rs.getDouble("stavkaiznajmljivanja.iznos");
        double cenaPoDanu = rs.getDouble("stavkaiznajmljivanja.cenaPoDanu");
        Date datumPreuzimanja = rs.getDate("stavkaiznajmljivanja.datumPreuzimanja");
        Date datumPovratka = rs.getDate("stavkaiznajmljivanja.datumPovratka");
        int idIznajmljivanje = rs.getInt("stavkaiznajmljivanja.idIznajmljivanje");

        int idSkije = rs.getInt("skije.idSkije");
        String model = rs.getString("skije.model");
        double velicina = rs.getDouble("skije.velicina");
        double cena = rs.getDouble("skije.cena");
        String tip = rs.getString("skije.tip");
        
        Skije skije = new Skije(idSkije, model, velicina, cena, tip);

       Iznajmljivanje iznajmljivanje1 = new Iznajmljivanje();
        iznajmljivanje1.setIdIznajmljivanje(idIznajmljivanje);
          
        StavkaIznajmljivanja stavka = new StavkaIznajmljivanja(rb,  cenaPoDanu, datumPreuzimanja, 
                datumPovratka, iznos, skije, iznajmljivanje1);

        lista.add(stavka);
    }
    
    return lista;
    }

    @Override
    public String vratiKolonuZaInsert() {
return "rb, iznos, cenaPoDanu, datumPreuzimanja, datumPovratka, idSkije, idIznajmljivanje";
    }

    @Override
    public String vratiVrednostZaInsert() {
        java.sql.Date datumPr = new java.sql.Date(datumPreuzimanja.getTime());
        java.sql.Date datumPo = new java.sql.Date(datumPovratka.getTime());
return "'" + rb + "', '" + iznos + "', '" + cenaPoDanu + "', '" 
            + datumPr + "', '" + datumPo + "', " 
            + skije.getIdSkije() + ", " + iznajmljivanje.getIdIznajmljivanje();
    }

    @Override
    public String vratiPK() {
      System.out.println("PK :"+"rb = " + rb + " AND idIznajmljivanje = " + iznajmljivanje.getIdIznajmljivanje() );
     return "rb = " + rb + " AND idIznajmljivanje = " + iznajmljivanje.getIdIznajmljivanje();
     }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
         java.sql.Date datumPr = new java.sql.Date(datumPreuzimanja.getTime());
        java.sql.Date datumPo = new java.sql.Date(datumPovratka.getTime());
    return "cenaPoDanu = " + cenaPoDanu + ", datumPreuzimanja = '" + datumPr + "'" +
           ", datumPovratka = '" + datumPo + "'" +  ", iznos = " + iznos +  ", idSkije = " + skije.getIdSkije();
    }
    

}
