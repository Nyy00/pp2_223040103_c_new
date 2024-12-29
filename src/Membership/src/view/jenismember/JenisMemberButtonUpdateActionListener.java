package  Membership.src.view.jenismember;

import java.awt.event.*;
import  Membership.src.model.JenisMember;
import  Membership.src.dao.JenisMemberDao;

public class JenisMemberButtonUpdateActionListener implements ActionListener {
    private JenisMemberFrame jenisMemberFrame;
    private JenisMemberDao jenisMemberDao;

    public JenisMemberButtonUpdateActionListener(JenisMemberFrame jenisMemberFrame, JenisMemberDao jenisMemberDao) {
        this.jenisMemberFrame = jenisMemberFrame;
        this.jenisMemberDao = jenisMemberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JenisMember selectedJenisMember = jenisMemberFrame.getSelectedJenisMember();
        if (selectedJenisMember != null) {
            String nama = jenisMemberFrame.getNama();
            selectedJenisMember.setNama(nama);
            jenisMemberDao.update(selectedJenisMember);
            jenisMemberFrame.updateJenisMember(selectedJenisMember);
        }
    }
}