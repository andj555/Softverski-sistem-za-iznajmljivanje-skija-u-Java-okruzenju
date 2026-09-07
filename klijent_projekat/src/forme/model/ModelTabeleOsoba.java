/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Clanstvo;
import domen.Osoba;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Anđelija
 */
public class ModelTabeleOsoba extends AbstractTableModel{

    List<Osoba> lista;
    String[] kolone = {"id", "Ime", "Prezime", "Telefon", "Članstvo"};

    public ModelTabeleOsoba(List<Osoba> lista) {
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

        Osoba osoba = lista.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return osoba.getIdOsoba();
            case 1:
                return osoba.getIme();
            case 2:
                return osoba.getPrezime();
            case 3:
                return osoba.getTelefon(); 
                        
            case 4:
                return osoba.getClanstvo().getTipClanstva();
                
            default:
                return "NA";
        }
    }

    public List<Osoba> getLista() {
        return lista;
    }

    public void setLista(List<Osoba> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

   

}
