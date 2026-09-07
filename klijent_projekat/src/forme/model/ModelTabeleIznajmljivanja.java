/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Clanstvo;
import domen.Iznajmljivanje;
import domen.Osoba;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Anđelija
 */
public class ModelTabeleIznajmljivanja extends AbstractTableModel {

    List<Iznajmljivanje> lista = new ArrayList<>();
    String[] kolone = {"id", "Ukupan iznos", "Popust", "Datum realizacije", "Osoba", "Zaposleni"};

    public ModelTabeleIznajmljivanja(List<Iznajmljivanje> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;

    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Iznajmljivanje i = lista.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return i.getIdIznajmljivanje();
            case 1:
                return i.getUkupanIznos();
            case 2:
                return i.getPopust();
            case 3:
                return i.getDatumRealizacije(); 
            case 4:
                return i.getOsoba().getIme();               
            case 5:
                return i.getZaposleni().getIme();
                
            default:
                return "NA";
        }
    }

    public List<Iznajmljivanje> getLista() {
        return lista;
    }

    public void setLista(List<Iznajmljivanje> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }


    
}
