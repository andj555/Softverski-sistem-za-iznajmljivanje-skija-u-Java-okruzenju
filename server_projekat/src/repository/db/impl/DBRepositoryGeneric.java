/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import java.sql.*;
import java.util.*;
import repository.db.DBConnectionFactory;
import repository.db.DBRepository;
import domen.ApstraktniDomenskiObjekat;

/**
 *
 * @author Anđelija
 */
public class DBRepositoryGeneric implements DBRepository<ApstraktniDomenskiObjekat> {

    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        List<ApstraktniDomenskiObjekat> lista= new ArrayList<>();
        
        String upit = "SELECT * FROM " + param.vratiNazivTabele();
        
        if (uslov!=null){
        upit = upit + " " +uslov;
        }
        
        System.out.println(upit);
        PreparedStatement st =  DBConnectionFactory.getInstance().getConnection().prepareStatement(upit);
        ResultSet rs = st.executeQuery();
        lista = param.vratiListu(rs);
        rs.close();
        st.close();
        
        return lista;
    }

    @Override
    public void add(ApstraktniDomenskiObjekat param) throws Exception {
 String upit = "INSERT INTO " + param.vratiNazivTabele() + " (" + param.vratiKolonuZaInsert()
                + ") VALUES ( " + param.vratiVrednostZaInsert()+ ")";
        System.out.println(upit);
        PreparedStatement st = (PreparedStatement) DBConnectionFactory.getInstance().getConnection().prepareStatement(upit); 
        st.executeUpdate();
        st.close();
    }

    @Override
    public void edit(ApstraktniDomenskiObjekat param) throws Exception {
String upit = "UPDATE " + param.vratiNazivTabele() + " SET " + param.vratiVrednostZaIzmenu()
                +" WHERE "+param.vratiPK();
        System.out.println(upit);
        PreparedStatement st = DBConnectionFactory.getInstance().getConnection().prepareStatement(upit); 
        st.executeUpdate();
        st.close();
    }

    @Override
    public void delete(ApstraktniDomenskiObjekat param) throws Exception {
  String upit = "DELETE FROM " + param.vratiNazivTabele() + " WHERE " + param.vratiPK();
        System.out.println(upit);
        PreparedStatement st = DBConnectionFactory.getInstance().getConnection().prepareStatement(upit); 
        st.executeUpdate();
        st.close();
    }

    @Override
    public List<ApstraktniDomenskiObjekat> getAll() {
   List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        String upit = "SELECT * FROM";
        
        return lista;
    }

    @Override
    public int addReturnKey(ApstraktniDomenskiObjekat param) throws Exception {
     
    String upit = " INSERT INTO " + param.vratiNazivTabele() + " ( " + param.vratiKolonuZaInsert() + 
            " ) VALUES ( " + param.vratiVrednostZaInsert() +" ) ";
        
    PreparedStatement ps = DBConnectionFactory.getInstance().getConnection().prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
    ps.executeUpdate();
    
        ResultSet rs =  ps.getGeneratedKeys();
        
        int id= -1;
        
        if ( rs.next()) {
        
            id  =rs.getInt(1);
        } 
        rs.close();
        ps.close();
        
        return id;
    }
    
}  
