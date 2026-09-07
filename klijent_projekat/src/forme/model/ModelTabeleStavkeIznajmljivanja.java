/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Clanstvo;
import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Anđelija
 */
public class ModelTabeleStavkeIznajmljivanja extends AbstractTableModel {
 
    
   List<StavkaIznajmljivanja> lista;
    String[] kolone = {"rb", "Iznos", "Cena po danu", "Datum preuzimanja", "Datum povratka", "Skije"};

    public ModelTabeleStavkeIznajmljivanja(List<StavkaIznajmljivanja> lista) {
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

        StavkaIznajmljivanja s = lista.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return s.getRb();
            case 1:
                return s.getIznos();
            case 2:
                return s.getCenaPoDanu();
            case 3:
                return s.getDatumPreuzimanja(); 
            case 4:
                return s.getDatumPovratka();               
            case 5:
                return s.getSkije().getModel();
                
            default:
                return "NA";
        }
    }

    public List<StavkaIznajmljivanja> getLista() {
        return lista;
    }

    public void setLista(List<StavkaIznajmljivanja> lista) {
        this.lista = lista;
         fireTableDataChanged();
    }

    public void dodajStavke(StavkaIznajmljivanja stavka) {
        int rb= lista.size() + 1;
        stavka.setRb(rb);
        lista.add(stavka);
        fireTableDataChanged();
    }

    public void obrisiStavku(StavkaIznajmljivanja stavka) {
        lista.remove(stavka);
        fireTableDataChanged();
    }

   
    
}
