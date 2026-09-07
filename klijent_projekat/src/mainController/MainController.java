/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainController;

import Kontroleri.LoginController;
import domen.Zaposleni;
import forme.ModForme;
import forme.UbaciOsobuForma;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikaziIznajmljivanjaForma;
import forme.PrikaziOsobuForma;
import forme.PromeniStavkuForma;
import forme.UbaciRadnuSmenuForma;
import java.util.HashMap;
import java.util.Map;
import kontroleri.UbaciOsobuController;
import kontroleri.GlavnaFormaController;
import kontroleri.PrikaziIznajmljivanjaController;
import kontroleri.PrikaziOsobuController;
import kontroleri.PromeniStavkuController;
import kontroleri.UbaciRadnuSmenuController;


/**
 *
 * @author Anđelija
 */
public class MainController {
    
    private static MainController instance;
    private LoginController loginController;
    private GlavnaFormaController GFController;
    private PrikaziOsobuController OsobaPrikaziController;
    private UbaciOsobuController ubaciOsobuController;
    private PrikaziIznajmljivanjaController PrikaziIznajmljivanjaController;
    private PromeniStavkuController UbaciStavkuController;
    private UbaciRadnuSmenuController UbaciRadnuSmenuController;
    private Zaposleni ulogovani;
    private Map<String, Object> parametri;

    public Zaposleni getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Zaposleni ulogovani) {
        this.ulogovani = ulogovani;
    }
    
    private MainController(){
    
        parametri= new HashMap<>();
    }
    
    public static MainController getInstance(){
    if (instance==null){
    instance = new MainController();
         }
    return instance;
    }

    public PrikaziIznajmljivanjaController getPrikaziIznajmljivanjaController() {
        return PrikaziIznajmljivanjaController;
    }

    public PrikaziOsobuController getOsobaPrikaziController() {
        return OsobaPrikaziController;
    }

    public GlavnaFormaController getGFController() {
        return GFController;
    }

    
    public void prikaziLF() {
     loginController = new LoginController(new LoginForma());
    loginController.prikaziLF();
    }

    public void prikaziGF() {
  GFController = new GlavnaFormaController(new GlavnaForma());
   GFController.prikaziGF();   
    }

    public void prikaziGF(ModForme modForme) {
        GFController = new GlavnaFormaController(new GlavnaForma());
        GFController.prikaziGF(modForme);
        
    }
    
    public void prikaziOsobaPrikaziF() {
    OsobaPrikaziController = new PrikaziOsobuController(new PrikaziOsobuForma());
   OsobaPrikaziController.prikaziOsobaPrikaziF();    
    }

    public void prikaziUbaciOsobuF(){
    ubaciOsobuController = new UbaciOsobuController(new UbaciOsobuForma());
    ubaciOsobuController.prikaziDodajOsobuF(ModForme.DODAJ);
    }

    public void dodajParametre(String str, Object obj){
    parametri.put(str, obj);
    }
    
    public Object vratiParametre(String str){
    return parametri.get(str);
    }

    public void prikaziPromeniOsobuF() {
    ubaciOsobuController = new UbaciOsobuController(new UbaciOsobuForma());
    ubaciOsobuController.prikaziDodajOsobuF(ModForme.PROMENI);   
    }
    
    public void prikaziIznajmljivanjaF() {
    PrikaziIznajmljivanjaController = new PrikaziIznajmljivanjaController(new PrikaziIznajmljivanjaForma());
    PrikaziIznajmljivanjaController.prikaziIznajmljivanjaF();    
    }

    public void prikaziPromeniStavkuF() {
        UbaciStavkuController = new PromeniStavkuController(new PromeniStavkuForma());
        UbaciStavkuController.prikaziPromeniStavkuF();
    }

     public void prikaziUbaciRadnuSmenuF() {
        UbaciRadnuSmenuController = new UbaciRadnuSmenuController(new UbaciRadnuSmenuForma());
        UbaciRadnuSmenuController.prikaziUbaciRadnuSmenuF();
    }

}
