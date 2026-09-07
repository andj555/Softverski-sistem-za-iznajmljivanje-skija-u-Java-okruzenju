/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;


import domen.Clanstvo;
import domen.Osoba;
import forme.PrikaziOsobuForma;
import forme.model.ModelTabeleOsoba;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author Anđelija
 */
public class PrikaziOsobuController {

    private final PrikaziOsobuForma opf;

    public PrikaziOsobuController(PrikaziOsobuForma opf) {
        this.opf = opf;
       addActionListener();
       addMouseListener();
       ucitajOsobe();
       popunicb();
    }

    public void prikaziOsobaPrikaziF() {
        opf.setVisible(true);
    }
 
    public void ucitajOsobe(){
        List<Osoba> osobe = komunikacija.Komunikacija.getInstance().ucitajOsobe();
        ModelTabeleOsoba mto = new ModelTabeleOsoba(osobe);
        opf.getTableosobe().setModel(mto);
    }

    private void addActionListener() {
        opf.obrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = opf.getTableosobe().getSelectedRow();
                if (red==-1){
                    JOptionPane.showMessageDialog(opf, "Sistem ne moze da nadje osobu", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }else{
                ModelTabeleOsoba mto = (ModelTabeleOsoba) opf.getTableosobe().getModel();
                Osoba osoba = mto.getLista().get(red);
 
                try{
                int potvrda =JOptionPane.showConfirmDialog(opf, "Da li se sigurni da zelite da obrisete?", "POTVRDA", 0, JOptionPane.YES_NO_OPTION);
                if (potvrda==JOptionPane.YES_OPTION){
                Komunikacija.getInstance().obrisiOsobu(osoba);
                JOptionPane.showMessageDialog(opf, "Sistem je obrisao osobu", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                ucitajOsobe();
                }
                }catch(Exception ex){
               JOptionPane.showMessageDialog(opf, "Sistem ne moze da obrise osobu", "GREŠKA", JOptionPane.ERROR_MESSAGE);

                }
              }
            }
        });
        
           opf.promeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = opf.getTableosobe().getSelectedRow();
                if (red==-1){
                    JOptionPane.showMessageDialog(opf, "Sistem ne moze da nadje osobu", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }else{
                ModelTabeleOsoba mto = (ModelTabeleOsoba) opf.getTableosobe().getModel();
                Osoba osoba = mto.getLista().get(red);
                
                mainController.MainController.getInstance().dodajParametre("Osoba", osoba);
                mainController.MainController.getInstance().prikaziPromeniOsobuF();
                }
            }
        
         });
           
            opf.pretraziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String ime = opf.getTxtime().getText().trim();
                String prezime = opf.getTxtprezime().getText().trim();
                String telefon = opf.getTxttelefon().getText().trim();
                String jmbg = opf.getTxtjmbg().getText().trim();
                Clanstvo clanstvo = null;
                if (opf.getCbclanstvo().getSelectedIndex() > 0) {
               clanstvo = (Clanstvo) opf.getCbclanstvo().getSelectedItem();
                }                
                
                Osoba o = new Osoba();
             o.setIme(ime);
             o.setPrezime(prezime);
             o.setTelefon(telefon);
             o.setJmbg(jmbg);
             o.setClanstvo(clanstvo);
        
            List<Osoba> lista  = Komunikacija.getInstance().pretraziOsobe(o);

        ModelTabeleOsoba mto = (ModelTabeleOsoba) opf.getTableosobe().getModel();
        mto.setLista(lista); 
       
          if (mto.getLista().isEmpty()) {
        JOptionPane.showMessageDialog(opf, "Sistem ne moze da nadje osobe po zadatim kriterijumima", "GRESKA", JOptionPane.ERROR_MESSAGE);
        } else {
         JOptionPane.showMessageDialog(opf, "Sistem je nasao osobe po zadatim kriterijumima", "USPEH", JOptionPane.INFORMATION_MESSAGE);
       } 
             }
        
         });
            
            opf.ponistiFilterAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetuj();
                ucitajOsobe(); 
            
            }
        
         });
    
    }
    

    private void popunicb() {
        
        List<Clanstvo> cl = Komunikacija.getInstance().ucitajClanstva();
        opf.getCbclanstvo().removeAllItems();
        opf.getCbclanstvo().addItem(null);
        for (Clanstvo c: cl){
        opf.getCbclanstvo().addItem(c);
        
    }
    }
    
   
    public void resetuj(){
    
    opf.getTxtime().setText("");
    opf.getTxtprezime().setText("");
    opf.getTxttelefon().setText("");
    opf.getTxtjmbg().setText("");
    
    opf.getCbclanstvo().setSelectedIndex(0);
   
    }
    
    private void addMouseListener() {
    opf.getTableosobe().addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          int red = opf.getTableosobe().getSelectedRow();

          if (red !=-1) {
              try {
                 int red1 = opf.getTableosobe().convertRowIndexToModel(red);
                 ModelTabeleOsoba mto = (ModelTabeleOsoba) opf.getTableosobe().getModel();
                 Osoba izabrana = mto.getLista().get(red1);
                 List<Osoba> osobe = komunikacija.Komunikacija.getInstance().ucitajOsobe();

                  boolean postoji = false;
                   for (Osoba o : osobe) {
                    if (o.getIdOsoba()==(izabrana.getIdOsoba())) {
                         
                        postoji = true;
                        break;
                      }
                   }

                 if (!postoji) {
                     JOptionPane.showMessageDialog(opf, "Sistem ne moze da nadje osobu", "GRESKA", JOptionPane.ERROR_MESSAGE);
                     mto.setLista(osobe);
                     mto.fireTableDataChanged();
                  } else {
                     JOptionPane.showMessageDialog(opf, "Sistem je nasao osobu ", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                   }
                } catch (Exception ex) {
                   JOptionPane.showMessageDialog(opf, "Sistem ne moze da nadje osobu", "GRESKA", JOptionPane.ERROR_MESSAGE);
              }
           }
        
        }
    });
}

}