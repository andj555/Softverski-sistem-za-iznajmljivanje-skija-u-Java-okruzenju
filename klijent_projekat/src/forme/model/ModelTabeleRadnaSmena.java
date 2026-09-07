/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.RadnaSmena;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Anđelija
 */
public class ModelTabeleRadnaSmena extends AbstractTableModel {
    List<RadnaSmena> lista;
    String[] kolone = {"Tip smene", "Vreme početka", "Vreme kraja"};

    public ModelTabeleRadnaSmena(List<RadnaSmena> lista) {
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

        RadnaSmena rs = lista.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return rs.getTipSmene();
            case 1:
                return rs.getVremePocetka();
            case 2:
                return rs.getVremeKraja();
           
            default:
                return "NA";
        }
    }

    public List<RadnaSmena> getLista() {
        return lista;
    }

    public void setLista(List<RadnaSmena> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

   

}

