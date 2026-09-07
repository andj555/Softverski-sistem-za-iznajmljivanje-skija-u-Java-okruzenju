/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import Kontroleri.LoginController;
import domen.Clanstvo;
import domen.Osoba;
import domen.Skije;
import domen.Zaposleni;
import forme.ModForme;
import forme.UbaciOsobuForma;
import forme.PrikaziOsobuForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import mainController.MainController;

/**
 *
 * @author Anđelija
 */
public class UbaciOsobuController {

    
       private final UbaciOsobuForma dof;

    public UbaciOsobuController(UbaciOsobuForma dof) {
        this.dof = dof;
       addActionListener();
    }

    
    public void prikaziDodajOsobuF(ModForme f) {
        popunicb();
        pripremiFormu(f);
        dof.setVisible(true);
    }
     
    
    public static List<Clanstvo> ucitajClanstva() {
        List<Clanstvo> clanstva = Komunikacija.getInstance().ucitajClanstva();
        return clanstva;
    }

    private void addActionListener() {
        
        dof.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   dodaj(e);       
                   JOptionPane.showMessageDialog(dof, "Sistem je zapamtio osobu", "USPEH", JOptionPane.INFORMATION_MESSAGE);  
                   MainController.getInstance().getGFController().ucitajCB(); 
                   dof.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(dof, "Sistem ne moze da zapamti osobu", "GREŠKA", JOptionPane.ERROR_MESSAGE);
     
                }
            }
            
            private void dodaj(ActionEvent e) throws Exception {
                String ime = dof.getTxtime().getText().trim();
                String prezime = dof.getTxtprezime().getText().trim();
                String telefon = dof.getTxttelefon().getText().trim();
                String jmbg = dof.getTxtjmbg().getText().trim();
                Clanstvo clanstvo = (Clanstvo) dof.getCbclanstvo().getSelectedItem();
                
               
                if (ime.matches(".*\\d.*")) {
               JOptionPane.showMessageDialog(dof, "Ime ne sme da ima cifre", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                throw new Exception("Ime ne sme da ima brojeve");
                 }
                
                if (prezime.matches(".*\\d.*")) {
                JOptionPane.showMessageDialog(dof, "Prezime ne sme da ima cifre", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                throw new Exception("Prezime ne sme da ima brojeve");
                }
                
                if (!telefon.matches("\\d+")) {               
                 JOptionPane.showMessageDialog(dof, "Telefon ne sme da ima slova", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                throw new Exception("Telefon ne sme da ima slova");
                }
               
                 if (!jmbg.matches("\\d+")) {               
                 JOptionPane.showMessageDialog(dof, "JMBG ne sme da ima slova", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                throw new Exception("Jmbg ne sme da ima slova");
                }
                
                Osoba osoba = new Osoba(-1, ime, prezime, telefon, jmbg, clanstvo);
               
                    Komunikacija.getInstance().dodajOsobu(osoba);
                
            }
            
            
        });
        
        dof.promeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   promeni(e);
                   JOptionPane.showMessageDialog(dof, "Sistem je zapamtio osobu", "USPEH", JOptionPane.INFORMATION_MESSAGE); 
                   dof.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(dof, "Sistem ne moze da zapamti osobu", "GREŠKA", JOptionPane.ERROR_MESSAGE);
     
                }
            }
            
            private void promeni(ActionEvent e) throws Exception {
                
                 Osoba o1 = (Osoba) MainController.getInstance().vratiParametre("Osoba");
                
                int id = o1.getIdOsoba();
                String ime = dof.getTxtime().getText().trim();
                String prezime = dof.getTxtprezime().getText().trim();
                String telefon = dof.getTxttelefon().getText().trim();
                String jmbg = dof.getTxtjmbg().getText().trim();
                Clanstvo clanstvo = (Clanstvo) dof.getCbclanstvo().getSelectedItem();
                
                Osoba osoba = new Osoba(id, ime, prezime, telefon, jmbg, clanstvo);
                
                    Komunikacija.getInstance().promeniOsobu(osoba);
                    MainController.getInstance().getOsobaPrikaziController().ucitajOsobe();
                   
            }
            
            
        });
    }

    private void pripremiFormu(ModForme f) {
        switch(f){
                case DODAJ:
                   
                    dof.getBtndodaj().setVisible(true);
                    dof.getBtndodaj().setEnabled(true);
                    dof.getBtnpromeni().setVisible(false);
                    
                break;
                
                case PROMENI:
                    dof.getBtndodaj().setVisible(false);
                    dof.getBtnpromeni().setEnabled(true);
                    dof.getBtnpromeni().setVisible(true);  
                    
                    Osoba o = (Osoba) MainController.getInstance().vratiParametre("Osoba");
                    
                    dof.getTxtime().setText(o.getIme());
                    dof.getTxtprezime().setText(o.getPrezime());
                    dof.getTxttelefon().setText(o.getTelefon());
                    dof.getTxtjmbg().setText(o.getJmbg());
                    dof.getCbclanstvo().setSelectedItem(o.getClanstvo());
                            
                    break;
                default:
                    throw new AssertionError();
                    
        }            
    }

    private void popunicb() {
        List<Clanstvo>clanstva = ucitajClanstva();
        dof.getCbclanstvo().removeAllItems();
        for (Clanstvo c : clanstva){
        dof.getCbclanstvo().addItem(c);
        }
    }
    
}
