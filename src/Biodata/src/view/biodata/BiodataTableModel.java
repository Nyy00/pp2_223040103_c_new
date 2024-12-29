package Biodata.src.view.biodata;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import Biodata.src.model.Biodata;

class BiodataTableModel extends AbstractTableModel {
    private String[] columnNames = { "Nama", "NRP", "Alamat" };
    private List<Biodata> data;

    public BiodataTableModel(List<Biodata> data) {
        this.data = data;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Biodata rowItem = data.get(row);
        switch (col) {
            case 0:
                return rowItem.getNama();
            case 1:
                return rowItem.getNrp();
            case 2:
                return rowItem.getAlamat();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public Biodata getBiodataAt(int row) {
        return data.get(row);
    }

    public void add(Biodata value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void update(Biodata value) {
        int index = data.indexOf(value);
        if (index != -1) {
            data.set(index, value);
            fireTableRowsUpdated(index, index);
        }
    }

    public void remove(Biodata value) {
        int index = data.indexOf(value);
        if (index != -1) {
            data.remove(index);
            fireTableRowsDeleted(index, index);
        }
    }
}
