package  Membership.src.view.member;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import  Membership.src.model.Member;
import  Membership.src.dao.MemberDao;

public class MemberButtonDeleteActionListener implements ActionListener {
    private MemberFrame memberFrame;
    private MemberDao memberDao;

    public MemberButtonDeleteActionListener(MemberFrame memberFrame, MemberDao memberDao) {
        this.memberFrame = memberFrame;
        this.memberDao = memberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Member selectedMember = memberFrame.getSelectedMember();
        if (selectedMember == null) {
            memberFrame.showAlert("Pilih member yang ingin dihapus.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(memberFrame,
                "Apakah Anda yakin ingin menghapus member ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            memberDao.delete(selectedMember);
            memberFrame.deleteMember(selectedMember);
            memberFrame.showAlert("Member berhasil dihapus.");
        }
    }
}