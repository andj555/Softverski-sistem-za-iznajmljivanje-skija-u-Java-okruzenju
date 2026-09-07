/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Iznajmljivanje;
import domen.Osoba;
import domen.StavkaIznajmljivanja;
import domen.Zaposleni;
import forme.ModForme;
import forme.PrikaziIznajmljivanjaForma;
import forme.PrikaziOsobuForma;
import forme.model.ModelTabeleIznajmljivanja;
import forme.model.ModelTabeleOsoba;
import forme.model.ModelTabeleStavkeIznajmljivanja;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class PrikaziIznajmljivanjaController {
    
     private final PrikaziIznajmljivanjaForma pof;

    public PrikaziIznajmljivanjaController(PrikaziIznajmljivanjaForma pof) {
        this.pof = pof;
       addActionListener();
      addMouseListener();
    }

    public void prikaziIznajmljivanjaF() {
        ucitajIznajmljivanja();
        popuniCb();
        pof.setVisible(true);
    }

    public PrikaziIznajmljivanjaForma getPof() {
        return pof;
    }
 
    public void ucitajIznajmljivanja(){
        List<Iznajmljivanje> iznajmljivanje = komunikacija.Komunikacija.getInstance().ucitajIznajmljivanja();
        ModelTabeleIznajmljivanja mti = new ModelTabeleIznajmljivanja(iznajmljivanje);
        pof.getTableiznajmljivanje().setModel(mti);
   

        List<StavkaIznajmljivanja> stavke = new ArrayList<>();
        ModelTabeleStavkeIznajmljivanja mtsi = new ModelTabeleStavkeIznajmljivanja(stavke);
        pof.getTablestavkaiznajmljivanja().setModel(mtsi);
    }
    
    private void popuniCb() {
        
        List<Osoba> osobe = komunikacija.Komunikacija.getInstance().ucitajOsobe();
        pof.getCbosoba().removeAllItems();
        pof.getCbosoba().addItem(null);
        for (Osoba o: osobe){
        pof.getCbosoba().addItem(o);
        }
        
        List<Zaposleni> zaposleni = Komunikacija.getInstance().ucitajZaposlene();
        pof.getCbzaposleni().removeAllItems();
        pof.getCbzaposleni().addItem(null);
        for (Zaposleni z: zaposleni){
        pof.getCbzaposleni().addItem(z);
     
        }
    }

    private void addActionListener() {
        
        pof.promeniIznajmljivanjeAddActionListener(new ActionListener(){
        
        @Override
        public void actionPerformed(ActionEvent e){
        
        int red = pof.getTableiznajmljivanje().getSelectedRow();
        if (red==-1){
            JOptionPane.showMessageDialog(pof, "Sistem ne može da nadje iznajmljivanje", "GREŠKA", JOptionPane.ERROR_MESSAGE);
        
        }else {
            ModelTabeleIznajmljivanja mti = (ModelTabeleIznajmljivanja) pof.getTableiznajmljivanje().getModel();
            Iznajmljivanje iznajmljivanje = mti.getLista().get(red);
           
            MainController.getInstance().dodajParametre("iznajmljivanje", iznajmljivanje);
            MainController.getInstance().prikaziGF(ModForme.PROMENI);
            
          }
        }
        
        });
        
        pof.pretraziIznajmljivanjaAddActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int id=0;
        String stringid = pof.getTxtid().getText().trim();
        if (!stringid.isEmpty()) {
         try {
        id  = Integer.parseInt(stringid);
       } catch (Exception ex) {
            Logger.getLogger(PrikaziIznajmljivanjaController.class.getName()).log(Level.SEVERE, null, ex);
           return;
       }
        }
       
        Osoba osoba = null;
        if (pof.getCbosoba().getSelectedIndex() > 0) { 
    osoba = (Osoba) pof.getCbosoba().getSelectedItem();
        }

        Zaposleni zaposleni = null;
        if (pof.getCbzaposleni().getSelectedIndex() > 0) {
    zaposleni = (Zaposleni) pof.getCbzaposleni().getSelectedItem();
        }
        
	String datum = pof.getTxtdatum().getText().trim();
        java.sql.Date datum1 = null;

        if (!datum.isEmpty()) {
    try {
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            datum1 = new java.sql.Date(sdf.parse(datum).getTime());
        } catch (Exception ex) {
         JOptionPane.showMessageDialog(pof, "Sistem ne može da nađe iznajmljivanja po zadatim kriterijumima", "GREŠKA", JOptionPane.ERROR_MESSAGE);
        return; 
        }
        }

      Iznajmljivanje i = new Iznajmljivanje();
        i.setIdIznajmljivanje(id);
        i.setOsoba(osoba);
        i.setZaposleni(zaposleni);
        i.setDatumRealizacije(datum1);

        List<Iznajmljivanje> lista = Komunikacija.getInstance().pretraziIznajmljivanja(i);

            ModelTabeleIznajmljivanja mti = (ModelTabeleIznajmljivanja) pof.getTableiznajmljivanje().getModel();
            mti.setLista(lista);
            
        if (mti.getLista().isEmpty()) {
            JOptionPane.showMessageDialog(pof, "Sistem ne može da nađe iznajmljivanja po zadatim kriterijumima", "GREŠKA", JOptionPane.ERROR_MESSAGE);
        } else {
          JOptionPane.showMessageDialog(pof, "Sistem je našao iznajmljivanja po zadatim kriterijumima ", "USPEH", JOptionPane.INFORMATION_MESSAGE);
        }

    }
});
        
            pof.ponistiFilterAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pof.getTxtid().setText("");
                pof.getTxtdatum().setText("");
              pof.getCbosoba().setSelectedIndex(0);
              pof.getCbzaposleni().setSelectedIndex(0);
                  ucitajIznajmljivanja();
        
            }
         });
    
              pof.prikaziStavkeAddActionListener(new ActionListener()  {
        @Override
        public void actionPerformed(ActionEvent e){
        int red = pof.getTableiznajmljivanje().getSelectedRow();
        if (red!= -1){
            try{
        ModelTabeleIznajmljivanja mti = (ModelTabeleIznajmljivanja) pof.getTableiznajmljivanje().getModel();
        Iznajmljivanje i  = mti.getLista().get(red);
            System.out.println("KLIKNUT RED : "+i);
        List<StavkaIznajmljivanja> stavke = i.getStavkeIznajmljivanja();
            System.out.println("STAVKE : " + stavke);
        if (stavke == null) {
               stavke = new ArrayList<>();
           }
        
        ModelTabeleStavkeIznajmljivanja mtsi = new ModelTabeleStavkeIznajmljivanja(stavke);
        pof.getTablestavkaiznajmljivanja().setModel(mtsi);
        
            }catch(Exception ex){   
       JOptionPane.showMessageDialog(pof, "Sistem ne može da nađe iznajmljivanje ", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            }
          }else{
       JOptionPane.showMessageDialog(pof, "Sistem ne može da nađe iznajmljivanje ", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            
        }
      }
        });
    }   
          private void addMouseListener() {
         pof.getTableiznajmljivanje().addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int red = pof.getTableiznajmljivanje().getSelectedRow();

            if (red != -1) {
                try {
                    int red1= pof.getTableiznajmljivanje().convertRowIndexToModel(red);
                    ModelTabeleIznajmljivanja mti = (ModelTabeleIznajmljivanja) pof.getTableiznajmljivanje().getModel();
                    Iznajmljivanje izabrano = mti.getLista().get(red1);
    
                List<Iznajmljivanje> iznajmljivanja = komunikacija.Komunikacija.getInstance().ucitajIznajmljivanja();
    
                boolean postoji = false;
                for (Iznajmljivanje iz : iznajmljivanja) {
                    if (iz.getIdIznajmljivanje()==(izabrano.getIdIznajmljivanje())) {
                          postoji = true;
                          break;
                     }
                }
                  if (!postoji) { 
                   JOptionPane.showMessageDialog(pof,  "Sistem ne može da nađe iznajmljivanje ",   "GRESKA",   JOptionPane.ERROR_MESSAGE);
                 
                  mti.setLista(iznajmljivanja);
                   mti.fireTableDataChanged();
                  }  else {
                      
                 JOptionPane.showMessageDialog(pof,  "Sistem je našao iznajmljivanje ",   "USPEH",   JOptionPane.INFORMATION_MESSAGE);     
                  }
                } catch(Exception ex){
                  JOptionPane.showMessageDialog(pof,  "Sistem ne može da nađe iznajmljivanje ",   "GRESKA",   JOptionPane.ERROR_MESSAGE);
                   
                }
             }
          }
      });
    }
     
}
