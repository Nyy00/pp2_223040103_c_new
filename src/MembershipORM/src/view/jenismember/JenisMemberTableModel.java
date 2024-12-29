package MembershipORM.src.view.jenismember;

import MembershipORM.src.model.JenisMember;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class JenisMemberTableModel extends AbstractTableModel {
    private String[] columnNames = {"Nama"};
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
        if (col == 0) {
            return rowItem.getNama();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
      return false;
    }
    
    public void addJenisMember(JenisMember jenisMember) {
      data.add(jenisMember);  // Add to the list of data
      fireTableRowsInserted(data.size() - 1, data.size() - 1);  // Notify table that a row is added
  }

    public void remove(int row) {
        data.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public void update(int row, JenisMember jenisMember) {
        data.set(row, jenisMember);
        fireTableRowsUpdated(row, row);
    }


    public int getSelectedRow() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getSelectedRow'");
    }
}
