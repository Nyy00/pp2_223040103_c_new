package MembershipORM.src.view.member;

import MembershipORM.src.dao.MemberDao;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberDeleteButtonActionListener implements ActionListener {

    private MemberFrame memberFrame;
    private MemberDao memberDao;

    public MemberDeleteButtonActionListener(MemberFrame memberFrame, MemberDao memberDao) {
        this.memberFrame = memberFrame;
        this.memberDao = memberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the selected row from the table
        int selectedRow = memberFrame.getTable().getSelectedRow();

        if (selectedRow != -1) {
            // Get the ID of the selected Member (assumed to be in the first column)
            String memberIdStr = memberFrame.getTable().getValueAt(selectedRow, 0).toString();

            // Convert the ID to int
            int memberId = Integer.parseInt(memberIdStr);

            // Perform delete operation
            memberDao.delete(memberId);

            // Remove from table model and clear fields
            memberFrame.getTableModel().removeMember(selectedRow);
            memberFrame.getTextFieldNama().setText("");

            JOptionPane.showMessageDialog(memberFrame, "Member deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(memberFrame, "Please select a Member to delete.");
        }
    }
}
