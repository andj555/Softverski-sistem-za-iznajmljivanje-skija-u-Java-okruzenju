/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Iznajmljivanje;
import domen.Osoba;
import domen.Skije;
import domen.StavkaIznajmljivanja;
import forme.ModForme;
import forme.PromeniStavkuForma;
import forme.model.ModelTabeleIznajmljivanja;
import forme.model.ModelTabeleStavkeIznajmljivanja;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import mainController.MainController;

/**
 *
 * @author Anđelija
 */
public class PromeniStavkuController {

    private final PromeniStavkuForma usf;

    public PromeniStavkuController(PromeniStavkuForma usf) {
        this.usf = usf;
       addActionListener();
    }

    
    public void prikaziPromeniStavkuF() {
        
        pripremiFormu();
        usf.setVisible(true);
    }
    
    private void addActionListener() {

        
        usf.promeniStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //    int potvrda =JOptionPane.showConfirmDialog(usf, "Da li se sigurni da zelite da promenite?", "POTVRDA", 0, JOptionPane.YES_NO_OPTION);
                
                try {
                 //   if (potvrda==JOptionPane.YES_OPTION){
                   promeni(e);
                   JOptionPane.showMessageDialog(usf, "Sistem je zapamtio izmenu", "USPEH", JOptionPane.INFORMATION_MESSAGE); 
                   usf.dispose();
                 //   }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(usf, "Sistem ne moze da zapamti izmenu", "GRESKA", JOptionPane.ERROR_MESSAGE);
                        
                }
            }
            
            private void promeni(ActionEvent e) throws Exception {
                

            StavkaIznajmljivanja stavka = (StavkaIznajmljivanja) MainController.getInstance().vratiParametre("stavka");

                LocalDate datum_1=null;
                LocalDate datum_2=null;
                 java.sql.Date datumpr=null;
                  java.sql.Date datumpo=null;
               try {
        String datum1 = usf.getTxtdatumpreuzimanja().getText().trim();
           
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
          datum_1 = LocalDate.parse(datum1, dtf); 
          datumpr = java.sql.Date.valueOf(datum_1);
        } catch (Exception ex) {
        JOptionPane.showMessageDialog(usf, "Nevalidan datum", "GRESKA", JOptionPane.ERROR_MESSAGE);
        throw new Exception();
        }
                try {
        String datum2 = usf.getTxtdatumpovratka().getText().trim();
           
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         datum_2 = LocalDate.parse(datum2, dtf); 
        datumpo = java.sql.Date.valueOf(datum_2); 
        } catch (Exception ex) {
        JOptionPane.showMessageDialog(usf, "Nevalidan datum", "GRESKA", JOptionPane.ERROR_MESSAGE);
        throw new Exception();
        
        } 
                Skije skije = (Skije) usf.getCbskije().getSelectedItem();
                Double cenapodanu = skije.getCena();
                int brojDana = (int) (ChronoUnit.DAYS.between(datum_1, datum_2) + 1);                
                Double iznos = cenapodanu * brojDana;
                
                ModelTabeleStavkeIznajmljivanja mtsi = (ModelTabeleStavkeIznajmljivanja) MainController.getInstance().getGFController().getGf().getTablestavke().getModel();

               for (StavkaIznajmljivanja s : mtsi.getLista()) {
                if (s.getRb() == stavka.getRb()) {
                  s.setSkije(skije);
                  s.setCenaPoDanu(cenapodanu);
                  s.setDatumPreuzimanja(datumpr);
                  s.setDatumPovratka(datumpo);
                  s.setIznos(iznos);
                  break;
                  }
               }
                
                mtsi.fireTableDataChanged();
                MainController.getInstance().getGFController().izracunajUkIznos();

            }
            
            
        });
    }

    private void pripremiFormu() {
        ucitajcb();
       
                    usf.getTxtid().setEnabled(false);                    
                    usf.getTxtrb().setEnabled(false);
                    usf.getBtnpromenistavku().setEnabled(true);
                    usf.getBtnpromenistavku().setVisible(true);  
                    
                    StavkaIznajmljivanja s = (StavkaIznajmljivanja) MainController.getInstance().vratiParametre("stavka");
                    System.out.println("stavka :"+s);
                    
                    if (s.getIznajmljivanje() != null) {
                    usf.getTxtid().setText(String.valueOf(s.getIznajmljivanje().getIdIznajmljivanje()));
                    } else {
                  usf.getTxtid().setText("");
                    }
                    usf.getTxtrb().setText(String.valueOf(s.getRb()));
                    
              DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            java.sql.Date datumPreuzimanja = (java.sql.Date) s.getDatumPreuzimanja();
            java.sql.Date datumPovratka = (java.sql.Date) s.getDatumPovratka();

            usf.getTxtdatumpreuzimanja().setText(datumPreuzimanja.toLocalDate().format(formater));
            usf.getTxtdatumpovratka().setText(datumPovratka.toLocalDate().format(formater));
                usf.getCbskije().setSelectedItem(s.getSkije());
                     
    }

    private void ucitajcb() {
         List<Skije>skije = komunikacija.Komunikacija.getInstance().ucitajSkije();
        usf.getCbskije().removeAllItems();
        for (Skije s: skije){
        usf.getCbskije().addItem(s);
        }
       
    }
   
    
}
