package MembershipORM.src.view.jenismember;

import MembershipORM.src.dao.JenisMemberDao;
import MembershipORM.src.model.JenisMember;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JenisMemberUpdateButtonActionListener implements ActionListener {

    private JenisMemberFrame jenisMemberFrame;
    private JenisMemberDao jenisMemberDao;

    public JenisMemberUpdateButtonActionListener(JenisMemberFrame jenisMemberFrame, JenisMemberDao jenisMemberDao) {
        this.jenisMemberFrame = jenisMemberFrame;
        this.jenisMemberDao = jenisMemberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the selected row from the table
        int selectedRow = jenisMemberFrame.getTable().getSelectedRow();

        if (selectedRow != -1) {
            // Retrieve the updated data from the input fields
            String updatedNama = jenisMemberFrame.getTextFieldNama().getText();

            // Retrieve the ID of the selected JenisMember (assumed to be in the first column)
            String jenisMemberId = (String) jenisMemberFrame.getTable().getValueAt(selectedRow, 0);

            // Create the updated JenisMember object
            JenisMember updatedJenisMember = new JenisMember();
            updatedJenisMember.setId(jenisMemberId); // Set the ID
            updatedJenisMember.setNama(updatedNama); // Set the updated name

            // Perform update operation
            jenisMemberDao.update(updatedJenisMember);

            // Update table and clear fields
            jenisMemberFrame.updateJenisMember(updatedJenisMember, selectedRow);
            JOptionPane.showMessageDialog(jenisMemberFrame, "Jenis Member updated successfully.");
        } else {
            JOptionPane.showMessageDialog(jenisMemberFrame, "Please select a Jenis Member to update.");
        }
    }
}
