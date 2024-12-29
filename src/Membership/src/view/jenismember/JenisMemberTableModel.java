package  Membership.src.view.jenismember;


import java.util.List;
import javax.swing.table.*;
import  Membership.src.model.JenisMember;

class JenisMemberTableModel extends AbstractTableModel {
    private String[] columnNames = { "Nama" };
    private List<JenisMember> data;

    public JenisMemberTableModel(List<JenisMember> data) {
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
        JenisMember rowItem = data.get(row);
        switch (col) {
            case 0:
                return rowItem.getNama();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public JenisMember getJenisMemberAt(int row) {
        return data.get(row);
    }

    public void add(JenisMember value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void update(JenisMember value) {
        int index = data.indexOf(value);
        if (index != -1) {
            data.set(index, value);
            fireTableRowsUpdated(index, index);
        }
    }

    public void remove(JenisMember value) {
        int index = data.indexOf(value);
        if (index != -1) {
            data.remove(index);
            fireTableRowsDeleted(index, index);
        }
    }
}