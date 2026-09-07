/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import Kontroleri.LoginController;
import domen.Clanstvo;
import domen.Osoba;
import domen.RadnaSmena;
import forme.UbaciRadnuSmenuForma;
import forme.model.ModelTabeleRadnaSmena;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author Anđelija
 */
public class UbaciRadnuSmenuController {

    private final UbaciRadnuSmenuForma ursf;

    public UbaciRadnuSmenuController(UbaciRadnuSmenuForma ursf) {
        this.ursf = ursf;
        ucitajRadneSmene();
        addActionListener();
    }
    
    public void prikaziUbaciRadnuSmenuF() {
        
        ursf.setVisible(true);
    }

    private void addActionListener() {
        ursf.ubaciSmenuAddActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                  ubaci(e);                    
                   JOptionPane.showMessageDialog(ursf, "Sistem je zapamtio radnu smenu", "USPEH", JOptionPane.INFORMATION_MESSAGE);  
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(ursf, "Sistem ne moze da zapamti radnu smenu", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            private void ubaci(ActionEvent e) throws Exception {
                String tipSmene = ursf.getTxttipsmene().getText().trim();
                String vPocetka = ursf.getTxtvremepocetka().getText().trim();
                String vKraja = ursf.getTxtvremekraja().getText().trim();
                
                LocalTime vremePocetka = LocalTime.parse(vPocetka);
                LocalTime vremeKraja = LocalTime.parse(vKraja);
                
               RadnaSmena smena = new RadnaSmena(-1, tipSmene, vremePocetka, vremeKraja);
               
                Komunikacija.getInstance().ubaciRadnuSmenu(smena);
                ucitajRadneSmene();
            }
            
        });
    }

    private void ucitajRadneSmene() {
        List<RadnaSmena> smene = Komunikacija.getInstance().ucitajRadneSmene();
        ModelTabeleRadnaSmena mtrs = new ModelTabeleRadnaSmena(smene);
        ursf.getTableradnasmena().setModel(mtrs);
    }
    
}
