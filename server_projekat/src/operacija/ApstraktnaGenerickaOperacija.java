/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import repository.Repository;
import repository.db.DBRepository;
import repository.db.impl.DBRepositoryGeneric;

/**
 *
 * @author Anđelija
 */
public abstract class ApstraktnaGenerickaOperacija {
    
    protected final Repository dbbroker;
    
    public ApstraktnaGenerickaOperacija () {
    
    this.dbbroker= new DBRepositoryGeneric();
    
    }
    
    public final void izvrsi(Object objekat, String kljuc) throws Exception{
    
        try{
            preduslovi(objekat);
            
            zapocniTransakciju();
            
            izvrsiOperaciju(objekat, kljuc);
            
            potvrdiTransakciju();
        }catch(Exception e){
            ponistiTransakciju();
            e.printStackTrace();
            
            throw e;
        }
        finally{
           
        }
    
    }

    protected abstract void preduslovi(Object param) throws Exception;

    protected abstract void izvrsiOperaciju(Object param, String kljuc) throws Exception;

    
    private void zapocniTransakciju() throws Exception {
        ((DBRepository) dbbroker).connect();
    }

    private void potvrdiTransakciju() throws Exception {
    
        ((DBRepository) dbbroker).commit();
    }

    private void ponistiTransakciju() throws Exception{
        ((DBRepository) dbbroker).rollback();

    }

    private void ugasiKonekciju() throws Exception {
        ((DBRepository)dbbroker).disconnect();
    }
     
    
}
