package Biodata.src.view.biodata;

import java.awt.event.*;
import Biodata.src.model.Biodata;
import Biodata.src.dao.BiodataDao;
import javax.swing.JOptionPane;

public class BiodataButtonDeleteActionListener implements ActionListener {
    private BiodataFrame biodataFrame;
    private BiodataDao biodataDao;

    public BiodataButtonDeleteActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Biodata selectedBiodata = biodataFrame.getSelectedBiodata();
        if (selectedBiodata != null) {
            int confirm = JOptionPane.showConfirmDialog(biodataFrame, "Apakah Anda yakin ingin menghapus?", 
                                                        "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                biodataDao.delete(selectedBiodata);
                biodataFrame.deleteBiodata(selectedBiodata);
            }
        }
    }
}
