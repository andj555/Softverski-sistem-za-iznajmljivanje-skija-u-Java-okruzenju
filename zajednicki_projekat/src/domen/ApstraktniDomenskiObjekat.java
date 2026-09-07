/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.*;
import java.sql.*;

/**
 *
 * @author Anđelija
 */
public interface ApstraktniDomenskiObjekat extends Serializable {
    
    public String vratiNazivTabele();
    
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;
    
    public String vratiKolonuZaInsert();
    
    public String vratiVrednostZaInsert();
    
    public String vratiPK();
    
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception;
    
    public String vratiVrednostZaIzmenu();
            
            
            
            
}

