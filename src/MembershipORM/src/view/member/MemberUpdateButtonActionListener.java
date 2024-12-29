package MembershipORM.src.view.member;

import MembershipORM.src.dao.MemberDao;
import MembershipORM.src.model.JenisMember;
import MembershipORM.src.model.Member;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberUpdateButtonActionListener implements ActionListener {

    private MemberFrame memberFrame;
    private MemberDao memberDao;

    public MemberUpdateButtonActionListener(MemberFrame memberFrame, MemberDao memberDao) {
        this.memberFrame = memberFrame;
        this.memberDao = memberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the selected row from the table
        int selectedRow = memberFrame.getTableModel().getSelectedRow();
        
        if (selectedRow != -1) {
            // Retrieve the updated data from the input fields
            String updatedNama = memberFrame.getTextFieldNama().getText();
            JenisMember selectedJenisMember = memberFrame.getJenisMember();

            // Retrieve the ID of the selected Member (assumed to be in the first column)
            String memberId = (String) memberFrame.getTableModel().getValueAt(selectedRow, 0);

            // Create the updated Member object
            Member updatedMember = new Member();

            // Perform update operation
            memberDao.update(updatedMember);
            
            // Update table and clear fields
            memberFrame.getTableModel().setValueAt(updatedNama, selectedRow, 0);
            memberFrame.getTableModel().setValueAt(selectedJenisMember.getNama(), selectedRow, 1);
            JOptionPane.showMessageDialog(memberFrame, "Member updated successfully.");
        } else {
            JOptionPane.showMessageDialog(memberFrame, "Please select a Member to update.");
        }
    }
}
