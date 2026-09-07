/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author Anđelija
 */
public class Serverski_odgovor implements Serializable {
    
    private Object odgovor;

    public Serverski_odgovor() {
    }

    public Serverski_odgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }
    
}
