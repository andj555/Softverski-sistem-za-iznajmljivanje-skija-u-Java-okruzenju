/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import Kontroleri.LoginController;
import domen.Clanstvo;
import domen.Iznajmljivanje;
import domen.Osoba;
import domen.Skije;
import domen.StavkaIznajmljivanja;
import domen.Zaposleni;
import forme.GlavnaForma;
import forme.ModForme;
import forme.model.ModelTabeleIznajmljivanja;
import forme.model.ModelTabeleStavkeIznajmljivanja;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
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
public class GlavnaFormaController {
    
     private final GlavnaForma gf;
     List<Skije> skije;
     Zaposleni ulogovani;
     Double ukIznos=0.0;
     Iznajmljivanje trenutno;
     boolean ucitavanje = false;

    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
       addActionListeners();
    }

    public GlavnaForma getGf() {
        return gf;
    }

    public void prikaziGF() {
        
        gf.getBtnizmeniiznajmljivanje().setVisible(false);
       ulogovani = MainController.getInstance().getUlogovani();
       String ulogovaniIme = ulogovani.getIme()+" "+ulogovani.getPrezime();
     
        gf.setVisible(true);
        gf.getTxtulogovani().setText(ulogovaniIme);
        gf.getTxtukupaniznos().setText(ukIznos+"");
        
        ModelTabeleStavkeIznajmljivanja mtsi = new ModelTabeleStavkeIznajmljivanja(new ArrayList<StavkaIznajmljivanja>());
        gf.getTablestavke().setModel(mtsi);
        ucitajCB();

    }

    private void addActionListeners() {
        
        gf.dodajStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   dodajStavku(e);
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(gf, "Sistem ne moze da doda stavku", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        private void dodajStavku(ActionEvent e) throws Exception {
            
                Osoba osoba = (Osoba) gf.getCbosoba().getSelectedItem();
                Skije skije = (Skije) gf.getCbskije().getSelectedItem();
                String datumPreuzimanja = gf.getTxtdatumpreuzimanja().getText();
                String datumPovratka = gf.getTxtdatumpovratka().getText();
                Double cenaPoDanu = skije.getCena();
                LocalDate datum1 = null;
                LocalDate datum2=null;
                
            Date datumPr= null; 
                try{
            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        datum1 = LocalDate.parse(datumPreuzimanja, f);
        datumPr = java.sql.Date.valueOf(datum1);
            } catch (Exception ex) {
        JOptionPane.showMessageDialog(gf, "Nevalidan datum", "Greška", JOptionPane.ERROR_MESSAGE);
        return;
            }  
                
        Date datumPov= null; 
            try{
            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        datum2 = LocalDate.parse(datumPovratka, f);
        datumPov = java.sql.Date.valueOf(datum2);
            } catch (Exception ex) {
        JOptionPane.showMessageDialog(gf, "Nevalidan datum", "Greška", JOptionPane.ERROR_MESSAGE);
        return;
            } 
        
        if (datum1.isAfter(datum2)){
        throw new Exception("Redosled datuma nevalidan");
        }
            
        double brojDana = (double) (java.time.temporal.ChronoUnit.DAYS.between(datum1, datum2) + 1);
        Double iznos = cenaPoDanu * brojDana;
            
                StavkaIznajmljivanja stavka = new StavkaIznajmljivanja();
                stavka.setIznos(iznos);
                stavka.setCenaPoDanu(cenaPoDanu);
                stavka.setSkije(skije);
                stavka.setDatumPreuzimanja(datumPr);
                stavka.setDatumPovratka(datumPov);
               
            ModelTabeleStavkeIznajmljivanja mtsi = (ModelTabeleStavkeIznajmljivanja) gf.getTablestavke().getModel();
             mtsi.dodajStavke(stavka);
             izracunajUkIznos();
            }
        });
        
         gf.obrisiStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   obrisiStavku(e);
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(gf, "Sistem ne moze da obrise stavku", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
            }
                 
        private void obrisiStavku(ActionEvent e) {
            
            int red = gf.getTablestavke().getSelectedRow();
            if (red!=-1){
            ModelTabeleStavkeIznajmljivanja mtsi = (ModelTabeleStavkeIznajmljivanja) gf.getTablestavke().getModel();
            StavkaIznajmljivanja stavka = mtsi.getLista().get(red);
            mtsi.obrisiStavku(stavka);
            izracunajUkIznos();
            }else
            {
            JOptionPane.showMessageDialog(gf, "Sistem ne moze da obrise stavku", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            }
        }      
    });

          gf.dodajIznajmljivanjeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   ubaciIznajmljivanje(e);
                   JOptionPane.showMessageDialog(null, "Sistem je zapamtio iznajmljivanje", "USPEH", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                  JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti iznajmljivanje", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        private void ubaciIznajmljivanje(ActionEvent e) throws Exception {
            
           Iznajmljivanje iznajmljivanje = new Iznajmljivanje();
               
            ModelTabeleStavkeIznajmljivanja mtsi = (ModelTabeleStavkeIznajmljivanja) gf.getTablestavke().getModel();
            List<StavkaIznajmljivanja> listaStavki = mtsi.getLista();
            
            iznajmljivanje.setStavkeIznajmljivanja(listaStavki);
            Zaposleni zaposleni = (Zaposleni) gf.getCbzaposleni().getSelectedItem();
            iznajmljivanje.setZaposleni(zaposleni);
            Date datum = java.sql.Date.valueOf(LocalDate.now());
            iznajmljivanje.setDatumRealizacije(datum);
            Osoba osoba = (Osoba) gf.getCbosoba().getSelectedItem();
            iznajmljivanje.setOsoba(osoba);
            Double popust;
            
            if ( osoba.getClanstvo().getTipClanstva().toLowerCase().equals("deluxe")){
            popust = 0.10;
            }else if (osoba.getClanstvo().getTipClanstva().toLowerCase().equals("premium")){
            popust = 0.15;
            }else{
            popust=0.0;
            }
            
            iznajmljivanje.setPopust(popust);
            
            Double ukupanIznos = 0.0;
            for (StavkaIznajmljivanja s : listaStavki){
            ukupanIznos+=s.getIznos();
            }
            ukupanIznos = ukupanIznos * (1-popust);
            
            iznajmljivanje.setUkupanIznos(ukupanIznos);
            
             
                Komunikacija.getInstance().ubaciIznajmljivanje(iznajmljivanje);
                gf.getTxtukupaniznos().setText( String.valueOf(ukupanIznos));
                resetuj();
         }

        });
          
         gf.promeniIznajmljivanjeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                   promeniIznajmljivanje(e);
                   JOptionPane.showMessageDialog(null, "Sistem je zapamtio iznajmljivanje", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                  gf.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(gf, "Sistem ne moze da zapamti iznajmljivanje", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        private void promeniIznajmljivanje(ActionEvent e) throws Exception {
            
            Iznajmljivanje iznajmljivanje = new Iznajmljivanje();
               
            ModelTabeleStavkeIznajmljivanja mtsi = (ModelTabeleStavkeIznajmljivanja) gf.getTablestavke().getModel();
            List<StavkaIznajmljivanja> listaStavki = mtsi.getLista();
            
            iznajmljivanje.setIdIznajmljivanje(trenutno.getIdIznajmljivanje());
            for (StavkaIznajmljivanja s : listaStavki) {
            s.setIznajmljivanje(iznajmljivanje);
           }
            iznajmljivanje.setStavkeIznajmljivanja(listaStavki);
            Zaposleni zaposleni = (Zaposleni) gf.getCbzaposleni().getSelectedItem();
            iznajmljivanje.setZaposleni(zaposleni);
            Date datum = trenutno.getDatumRealizacije();
            iznajmljivanje.setDatumRealizacije(datum);
            Osoba osoba = (Osoba) gf.getCbosoba().getSelectedItem();
            iznajmljivanje.setOsoba(osoba);
            Double popust;
            
            if ( osoba.getClanstvo().getTipClanstva().toLowerCase().equals("deluxe")){
            popust = 0.10;
            }else if (osoba.getClanstvo().getTipClanstva().toLowerCase().equals("premium")){
            popust = 0.15;
            }else{
            popust=0.0;
            }
            
            iznajmljivanje.setPopust(popust);
            
            Double ukupanIznos = 0.0;
            for (StavkaIznajmljivanja s : listaStavki){
            ukupanIznos+=s.getIznos();
            }
            ukupanIznos = ukupanIznos * (1-popust);
            
            iznajmljivanje.setUkupanIznos(ukupanIznos);
            
                System.out.println(iznajmljivanje);
                System.out.println(iznajmljivanje.getStavkeIznajmljivanja());
                
                Komunikacija.getInstance().promeniIznajmljivanje(iznajmljivanje);
                MainController.getInstance().getPrikaziIznajmljivanjaController().ucitajIznajmljivanja();
              
           }
        }); 
         
           gf.promeniStavkuAddActionListener(new ActionListener(){
        
        @Override
        public void actionPerformed(ActionEvent e){

        int red = gf.getTablestavke().getSelectedRow();
        ModelTabeleStavkeIznajmljivanja mtsi = (ModelTabeleStavkeIznajmljivanja) gf.getTablestavke().getModel();
        if (red==-1){
            JOptionPane.showMessageDialog(gf, "Niste izabrali stavku", "GRESKA", JOptionPane.ERROR_MESSAGE);
        
        }else {
           StavkaIznajmljivanja stavkaZaIzmenu = mtsi.getLista().get(red);

    MainController.getInstance().dodajParametre("stavka", stavkaZaIzmenu);
    MainController.getInstance().prikaziPromeniStavkuF();
        }
          }
       
        });
         
         gf.getCbosoba().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           izracunajUkIznos(); 
          }
       });
     }             
    
    public void izracunajUkIznos() {
        
        if (ucitavanje){
        return; }
        
    ModelTabeleStavkeIznajmljivanja mtsi = (ModelTabeleStavkeIznajmljivanja) gf.getTablestavke().getModel();
    ukIznos = 0.0;

    for (StavkaIznajmljivanja s : mtsi.getLista()) {
        ukIznos += s.getIznos();
     }
    Double popust = 0.0;
    Osoba osoba = (Osoba) gf.getCbosoba().getSelectedItem();
    
    
    String tip = osoba.getClanstvo().getTipClanstva().toLowerCase();
      if (tip.equals("deluxe")) {
            popust = 0.10;
       } else if (tip.equals("premium" )) {
          popust = 0.15;
      }
    
    ukIznos = ukIznos * (1 - popust);
    gf.getTxtukupaniznos().setText(String.valueOf(ukIznos));
}
    
    public void ucitajCB() {
        ucitavanje=true;
        List<Osoba> osobe = komunikacija.Komunikacija.getInstance().ucitajOsobe();
        gf.getCbosoba().removeAllItems();
        for (Osoba o: osobe){
        gf.getCbosoba().addItem(o);
        }
        
        List<Zaposleni> zaposleni = Komunikacija.getInstance().ucitajZaposlene();
        gf.getCbzaposleni().removeAllItems();
        for (Zaposleni z: zaposleni){
        gf.getCbzaposleni().addItem(z);
        
    }
        
        skije = komunikacija.Komunikacija.getInstance().ucitajSkije();
        gf.getCbskije().removeAllItems();
        for (Skije s: skije){
        gf.getCbskije().addItem(s);
        }
       
        ucitavanje=false;
    }

    public void prikaziGF(ModForme modForme) {
        ucitavanje=true;
       ulogovani = MainController.getInstance().getUlogovani();
       String ulogovaniIme = ulogovani.getIme()+" "+ulogovani.getPrezime();
       
        gf.setVisible(true);
        gf.getTxtulogovani().setText(ulogovaniIme);
        
        ModelTabeleStavkeIznajmljivanja mtsi = new ModelTabeleStavkeIznajmljivanja(new ArrayList<StavkaIznajmljivanja>());
        gf.getTablestavke().setModel(mtsi);
          ucitajCB();
        if (modForme==modForme.PROMENI){
            gf.getBtnkreirajiznajmljivanje().setVisible(false);
            Iznajmljivanje i = (Iznajmljivanje) MainController.getInstance().vratiParametre("iznajmljivanje");
            mtsi.setLista(i.getStavkeIznajmljivanja());
            
            gf.getCbosoba().setSelectedItem(i.getOsoba());
            gf.getCbzaposleni().setSelectedItem(i.getZaposleni());            
       
            trenutno = i;
            izracunajUkIznos();
        }
        ucitavanje=false;
    }
    public void resetuj(){
    
    gf.getTxtdatumpreuzimanja().setText("");
    gf.getTxtdatumpovratka().setText("");
    gf.getTxtukupaniznos().setText("0.0"); 
    
    gf.getCbosoba().setSelectedIndex(0);
    gf.getCbzaposleni().setSelectedIndex(0);
    
    ModelTabeleStavkeIznajmljivanja mtsi = (ModelTabeleStavkeIznajmljivanja) gf.getTablestavke().getModel();
    mtsi.getLista().clear();         
    mtsi.fireTableDataChanged();

    }
   
}
