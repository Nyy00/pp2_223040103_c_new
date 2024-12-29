package MembershipORM.src.view.jenismember;

import MembershipORM.src.dao.JenisMemberDao;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JenisMemberDeleteButtonActionListener implements ActionListener {

    private JenisMemberFrame jenisMemberFrame;
    private JenisMemberDao jenisMemberDao;

    public JenisMemberDeleteButtonActionListener(JenisMemberFrame jenisMemberFrame, JenisMemberDao jenisMemberDao) {
        this.jenisMemberFrame = jenisMemberFrame;
        this.jenisMemberDao = jenisMemberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the selected row from the table
        int selectedRow = jenisMemberFrame.getTableModel().getSelectedRow();

        if (selectedRow != -1) {
            // Get the ID of the selected JenisMember (assumed to be in the first column)
            String jenisMemberId = (String) jenisMemberFrame.getTableModel().getValueAt(selectedRow, 0);

            // Perform delete operation
            jenisMemberDao.delete(Integer.valueOf(jenisMemberId));

            // Remove from table model and clear fields
            jenisMemberFrame.getTableModel().remove(selectedRow);
            jenisMemberFrame.getTextFieldNama().setText("");

            JOptionPane.showMessageDialog(jenisMemberFrame, "Jenis Member deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(jenisMemberFrame, "Please select a Jenis Member to delete.");
        }
    }
}