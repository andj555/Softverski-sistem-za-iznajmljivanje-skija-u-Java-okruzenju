/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Kontroleri;

import domen.Zaposleni;
import forme.LoginForma;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainController.MainController;

/**
 *
 * @author Anđelija
 */
public class LoginController {
    
  private final LoginForma lf;

    public LoginController(LoginForma lf) {
        this.lf = lf;
        addActionListeners();
    }


    private void addActionListeners() {
        
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    login(e);
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

            private void login(ActionEvent e) {
                String username = lf.getTxtuser().getText().trim();
                String password = String.valueOf(lf.getTxtpass().getPassword()).trim();
                Komunikacija.getInstance().konekcija();
                Zaposleni ulogovani = Komunikacija.getInstance().login(username, password);
                if (ulogovani == null) {
                    JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra nisu ispravni", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    
                } else {
                    MainController.getInstance().setUlogovani(ulogovani);
                    JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra su ispravni" ,"USPEH", JOptionPane.INFORMATION_MESSAGE);
                    MainController.getInstance().prikaziGF();
                    lf.dispose();
                     
                }

            }
        });

    }

    public void prikaziLF() {
        lf.setVisible(true);

    }

//    public void prikaziGF() {
//    }

    
}
